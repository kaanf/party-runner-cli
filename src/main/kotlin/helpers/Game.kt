package helpers

import java.util.*
import kotlin.system.exitProcess

class Game {
    private var currentRoom: Room? = null
    private var currentWall: Wall? = null
        get() {
            if (direction === "North") {
                currentWall = currentRoom?.northWall
            } else if (direction === "West") {
                currentWall = currentRoom?.westWall
            } else if (direction === "South") {
                currentWall = currentRoom?.southWall
            } else if (direction === "East") {
                currentWall = currentRoom?.eastWall
            }
            return field
        }
    private val rooms: MutableList<Room> = ArrayList()
    private val nextRoom = HashMap<Door, Room>()
    private val previousRoom = HashMap<Door, Room>()
    private val gameItems: MutableList<Item> = ArrayList()
    private val player = Player()
    private var navigation = 0
    private var direction: String? = null
        get() {
            when (navigation) {
                0 -> {
                    field = "North"
                }
                1 -> {
                    field = "West"
                }
                2 -> {
                    field = "South"
                }
                3 -> {
                    field = "East"
                }
            }
            return field
        }
    private var isTradeMode = false
    private val startTime: Long = System.currentTimeMillis()
    private val endTime: Long = startTime + 900000
    private fun setCurrentRoom(currentRoom: Room) {
        this.currentRoom = currentRoom
    }

    fun addRoom(room: Room) {
        rooms.add(room)
        setCurrentRoom(rooms[0])
        currentWall = currentRoom?.northWall
    }

    private val allItems: ArrayList<Item>
        get() = gameItems as ArrayList<Item>

    fun addItem(item: Item) {
        gameItems.add(item)
    }

    fun addDoor(door: Door, room1: Room, room2: Room) {
        previousRoom[door] = room1
        nextRoom[door] = room2
    }

    private fun timeOut(): Boolean {
        return System.currentTimeMillis() > endTime
    }

    private fun printTimeOutMessage() {
        println("Time out! Game Over.")
    }

    private val remainingTime:
            Unit
        get() {
            val remainingMinutes = (endTime - System.currentTimeMillis()) / 60000
            val minuteAfterPoint =
                (endTime.toDouble() - System.currentTimeMillis().toDouble()) / 60000 - remainingMinutes
            val secondsAfterPoint = minuteAfterPoint * 60
            val remainingSeconds = secondsAfterPoint.toInt()
            println("game ends within $remainingMinutes:$remainingSeconds minutes")
        }

    private fun printDirection() {
        println(direction)
    }

    private fun turnLeft() {
        if (navigation == 3) {
            navigation = 0
        } else {
            navigation++
        }
    }

    private fun turnRight() {
        if (navigation == 0) {
            navigation = 3
        } else {
            navigation--
        }
    }

    private fun moveForward() {
        if (currentWall is Door) {
            if ((currentWall as Door?)!!.isLocked()) {
                println(
                    "Door is locked, " + (currentWall as Door?)?.key?.name
                        .toString() + " is needed to unlock."
                )
            } else {
                currentRoom = if (currentRoom === nextRoom[currentWall]) {
                    previousRoom[currentWall]
                } else {
                    nextRoom[currentWall]
                }
                println("Successfully moved to adjacent room.")
                if (currentRoom!!.isExitPoint) {
                    println("Congratulations! Party Corgi now free.")
                    exitProcess(0)
                }
            }
        } else {
            println("No doors to go through.")
        }
    }

    private fun moveBackward() {
        turnLeft()
        turnLeft()
        if (currentWall is Door) {
            moveForward()
        } else {
            turnRight()
            turnRight()
            println("No doors to go through.")
        }
    }

    private fun playerStatus() {
        print(
            ("Direction : " + direction + "\n" + "Gold : "
                    + player.gold + "\n" + "Items : ")
        )
        player.printAllItems()
    }

    private fun look() {
        if (currentRoom!!.isLit) currentWall!!.display() else println("Room is dark , try \"SWITCHLIGHTS\" command OR  \"USE FLASHLIGHT FLASHLIGHTNAME\" command.")
    }

    private fun check() {
        if (currentRoom!!.isLit) {
            if (currentWall is Mirror) {
                if ((currentWall as Mirror?)!!.hasHiddenKey()) {
                    println((currentWall as Mirror?)?.hiddenKey?.name.toString() + " was acquired.")
                    (currentWall as Mirror?)?.hiddenKey?.let { player.addItem(it) }
                    (currentWall as Mirror?)?.hiddenKey = null
                } else {
                    println("No keys found")
                }
            } else if (currentWall is Chest) {
                if ((currentWall as Chest?)!!.isLocked()) {
                    println(
                        ("Chest is locked, " + (currentWall as Chest?)?.key?.name
                            .toString() + " is needed to unlock")
                    )
                } else {
                    if ((currentWall as Chest?)?.getItems()?.size!! > 0) {
                        for (item: Item in (currentWall as Chest?)?.getItems()!!) {
                            player.addItem(item)
                            print(item.name + ", ")
                        }
                        println(" was acquired!")
                        (currentWall as Chest?)?.getItems()?.clear()
                    } else {
                        println("No items in the Chest")
                    }
                }
            } else if (currentWall is Door) {
                if ((currentWall as Door?)!!.isLocked()) {
                    println(
                        ("Door is locked, " + (currentWall as Door?)?.key?.name
                            .toString() + " is needed to unlock")
                    )
                } else {
                    println("Door is open")
                }
            } else {
                println("check command is not valid here!")
            }
        } else println("Room is dark , try \"SWITCHLIGHTS\" command  OR \"USE FLASHLIGHT FLASHLIGHTNAME\" command")
    }

    private fun open() {
        if (currentWall is Door) {
            if ((currentWall as Door?)!!.isLocked()) {
                println(
                    ("Door is locked, " + (currentWall as Door?)?.key?.name
                        .toString() + " is needed to unlock.")
                )
            } else {
                println("door opened use FORWARD command to move to adjacent room.")
            }
        } else {
            println("OPEN Command is not valid, you are facing a " + currentWall?.name)
        }
    }

    private fun attack() {
        if (currentWall is Monster) {
            println("You have slain the ${(currentWall as Monster).name}.")
            if ((currentWall as Monster?)!!.hasHiddenKey()) {
                    println((currentWall as Monster?)?.hiddenKey?.name.toString() + " was acquired.")
                    (currentWall as Monster?)?.hiddenKey?.let { player.addItem(it) }
                    (currentWall as Monster?)?.hiddenKey = null
            } else {
                println("No keys found.")
            }
        }
    }

    private fun trade() {
        if (currentWall is Seller) {
            isTradeMode = true
            println("Trade Started")
            (currentWall as Seller).listItems()
        } else {
            println("TRADE command is only valid if you were facing a Seller.")
        }
    }

    private fun list() {
        if (currentWall is Seller) {
            if (isTradeMode) (currentWall as Seller).listItems() else {
                println("You are not in the trade mode.")
            }
        } else {
            println("LIST command is only valid if you were facing a Seller.")
        }
    }

    private fun buy(item: Item) {
        if (allItems.contains(item)) {
            if (currentWall is Seller) {
                if ((currentWall as Seller?)?.getItems()?.contains(item) == true) {
                    if (player.gold >= item.price) {
                        player.addItem(item)
                        player.decreaseGoldAmount(item.price)
                        (currentWall as Seller?)?.getItems()?.remove(item)
                        println("Successfully bought : " + item.name)
                    } else {
                        println("You don't have enough gold.")
                    }
                } else {
                    println("Seller does not have " + item.name)
                }
            } else {
                println("BUY command is only valid if you were facing a seller.")
            }
        } else {
            println("No such item, please enter existing item name.")
        }
    }

    private fun sell(item: Item) {
        if (allItems.contains(item)) {
            if (currentWall is Seller) {
                if (player.hasItem(item)) {
                    player.removeItem(item)
                    player.increaseGoldAmount(item.price)
                    (currentWall as Seller?)!!.addItem(item)
                    println("Successfully sold : " + item.name)
                } else {
                    println("Sorry! you don't have the item : " + item.name)
                }
            } else {
                println("SELL command is only valid if you were facing a seller.")
            }
        } else {
            println("No such item, please enter existing item name.")
        }
    }

    private fun finishTrade() {
        if (currentWall is Seller) {
            if (isTradeMode) {
                println("Trade Finished")
                isTradeMode = false
            } else {
                println("You are not in the trade mode.")
            }
        } else {
            println("FINISH TRADE command is only valid if you were facing a seller.")
        }
    }

    private fun useFlashLight(flashLight: FlashLight) {
        if (allItems.contains(flashLight)) {
            if (player.hasItem(flashLight)) {
                flashLight.switchLight()
                if (flashLight.isTurnedOn) {
                    if (!currentRoom!!.isLit) {
                        currentRoom!!.turnOnFlashlight()
                        println("Room is Lit now!")
                    } else {
                        println("Room is already lit.")
                    }
                } else {
                    println("FLASHLIGHT turned OFF!")
                }
            } else {
                println(
                    "You don't have the flash light " + flashLight.name + " try to find one."
                )
            }
        } else {
            println("No such flashlight, please enter existing flashlight name.")
        }
    }

    private fun useKey(key: Key) {
        if (allItems.contains(key)) {
            if (player.hasItem(key)) {
                when (currentWall) {
                    is Door -> {
                        (currentWall as Door?)!!.useKey(key)
                    }
                    is Chest -> {
                        (currentWall as Chest?)!!.useKey(key)
                    }
                    else -> {
                        println("No need to use key you are facing : " + currentWall?.name)
                    }
                }
            } else {
                println("You don't have " + key.name + " try to find it somewhere.")
            }
        } else {
            println("No such key, please enter existing key name.")
        }
    }

    private fun switchLights() {
        currentRoom!!.switchLights()
    }

    private fun quit() {
        println("Game Over")
        exitProcess(0)
    }

    fun readCommands(command: String) {
        if (timeOut()) {
            printTimeOutMessage()
            exitProcess(0)
        }
        val upperCasedCommand = command.uppercase(Locale.getDefault())
        if (upperCasedCommand.startsWith("USE")) {
            if (upperCasedCommand.contains(" ")) {
                var found = false
                for (item: Item in allItems) {
                    if ((upperCasedCommand.substring(upperCasedCommand.lastIndexOf(' '))).contains(
                            item.name.uppercase().replace(" ", "")
                        )
                    ) {
                        found = true
                        if (item is Key) {
                            useKey(item)
                        } else if (item is FlashLight) {
                            useFlashLight(item)
                        }
                    }
                }
                if (!found) {
                    println("Wrong item name. Please enter valid name.")
                }
            } else {
                println("Please make a space before item name.")
            }
        } else if (upperCasedCommand.startsWith("BUY") || upperCasedCommand.startsWith("SELL")) {
            if (upperCasedCommand.contains(" ")) {
                var found = false
                for (item: Item in allItems) {
                    if ((upperCasedCommand.substring(upperCasedCommand.lastIndexOf(' '))).contains(
                            item.name.uppercase().replace(" ", "")
                        )
                    ) {
                        found = true
                        if (upperCasedCommand.startsWith("BUY")) {
                            buy(item)
                        } else if (upperCasedCommand.startsWith("SELL")) {
                            sell(item)
                        }
                    }
                }
                if (!found) {
                    println("Wrong item name. Please enter valid name.")
                }
            } else {
                println("Please make a space before item name.")
            }
        } else {
            when (upperCasedCommand.replace(" ", "")) {
                "STATUS" -> playerStatus()
                "LOOK" -> look()
                "CHECK" -> check()
                "SWITCHLIGHTS" -> switchLights()
                "TIME" -> remainingTime
                "ATTACK" -> attack()
                "A" -> {
                    turnLeft()
                    look()
                }
                "D" -> {
                    turnRight()
                    look()
                }
                "W" -> moveForward()
                "S" -> moveBackward()
                "DIRECTION" -> printDirection()
                "OPEN" -> open()
                "TRADE" -> trade()
                "LIST" -> list()
                "FINISHTRADE" -> finishTrade()
                "QUIT" -> quit()
                else -> println(Colors.RED_BOLD_BRIGHT + "Invalid command." + Colors.RESET)
            }
        }
    }

    init {
        Runtime.getRuntime().exec("clear")
        println(Colors.CYAN_BOLD_BRIGHT + "Party Corgi" + Colors.RESET +  " got lost in a maze and needs to get to the party urgently. Open doors and chests by finding the necessary keys, save Party Corgi from the maze.")
        println(Colors.WHITE_BOLD + "You have" + Colors.YELLOW_BOLD_BRIGHT + " 15 minutes" + Colors.WHITE_BOLD + " to get out of the maze. Run!" + Colors.RESET)
    }
}
