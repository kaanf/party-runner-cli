package difficulty

import helpers.*
import java.io.IOException
import java.util.*

object Easy {
    @Throws(IOException::class)
    @JvmStatic
    fun main() {
        val redKey = Key()
        redKey.name = "Red Key"
        redKey.price = 80
        val blueKey = Key()
        blueKey.name = "Blue Key"
        blueKey.price = 95
        val yellowKey = Key()
        yellowKey.name = "Yellow Key"
        yellowKey.price = 90
        val greenKey = Key()
        greenKey.name = "Green Key"
        greenKey.price = 97
        val whiteKey = Key()
        whiteKey.name = "White Key"
        whiteKey.price = 200
        val superFlash = FlashLight()
        superFlash.name = "Super Flash"
        superFlash.price = 150

        val firstPlainWall = PlainWall()
        val secondPlainWall = PlainWall()
        val mirror1 = Mirror()
        mirror1.hiddenKey = greenKey

        val monster1 = Monster()
        monster1.name = "American Shorthair Cat"
        monster1.hiddenKey = greenKey
        val chest1 = Chest()
        chest1.lock()
        chest1.key = greenKey
        chest1.addItem(redKey)
        val chest2 = Chest()
        chest2.key = yellowKey
        val seller1 = Seller()
        seller1.addItem(blueKey)
        seller1.addItem(whiteKey)
        seller1.addItem(superFlash)
        val door1 = Door()
        door1.key = blueKey
        val door2 = Door()
        door2.lock()
        door2.key = redKey
        val door3 = Door()
        door3.lock()
        door3.key = whiteKey

        val room1 = Room()
        room1.addLights()
        room1.turnLightButton()
        room1.northWall = mirror1
        room1.westWall = monster1
        room1.southWall = door2
        room1.eastWall = door1
        val room2 = Room()
        room2.addLights()
        room2.northWall = secondPlainWall
        room2.westWall = door1
        room2.southWall = chest1
        room2.eastWall = firstPlainWall
        val room3 = Room()
        room3.addLights()
        room3.turnLightButton()
        room3.northWall = door2
        room3.westWall = door3
        room3.southWall = seller1
        room3.eastWall = chest2
        val room4 = Room()
        room4.addAsExitPoint()

        val game = Game()
        game.addItem(redKey)
        game.addItem(blueKey)
        game.addItem(greenKey)
        game.addItem(whiteKey)
        game.addItem(superFlash)
        game.addRoom(room1)
        game.addRoom(room2)
        game.addRoom(room3)
        game.addRoom(room4)
        game.addDoor(door1, room1, room2)
        game.addDoor(door2, room1, room3)
        game.addDoor(door3, room3, room4)

        val scanner = Scanner(System.`in`)
        while (true) {
            val command = scanner.nextLine()
            game.readCommands(command)
        }
    }
}

