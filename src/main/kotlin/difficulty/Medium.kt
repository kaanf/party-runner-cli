package difficulty

import helpers.*
import java.io.IOException
import java.util.*

object Medium {
    @Throws(IOException::class)
    @JvmStatic
    fun main() {
        val greyKey = Key()
        greyKey.name = "Grey Key"
        greyKey.price = 120

        val greenKey = Key()
        greenKey.name = "Green Key"
        greenKey.price = 75

        val orangeKey = Key()
        orangeKey.name = "Orange Key"
        orangeKey.price = 200

        val blackKey = Key()
        blackKey.name = "Black Key"
        blackKey.price = 97

        val pinkKey = Key()
        pinkKey.name = "Pink Key"
        pinkKey.price = 105

        val purpleKey = Key()
        purpleKey.name = "Purple Key"
        purpleKey.price = 137

        val whiteKey = Key()
        whiteKey.name = "White Key"
        whiteKey.price = 113

        val yellowKey = Key()
        yellowKey.name = "Yellow Key"
        yellowKey.price = 95

        val ledFlash = FlashLight()
        ledFlash.name = "Led Flash"
        ledFlash.price = 120

        val plainWall1 = PlainWall()
        val plainWall2 = PlainWall()

        val mirror1 = Mirror()
        mirror1.hiddenKey = (greyKey)

        val mirror2 = Mirror()

        val monster1 = Monster()
        monster1.hiddenKey = (purpleKey)
        monster1.name = "Norwegian Forest Cat"

        val chest1 = Chest()
        chest1.lock()
        chest1.key = (pinkKey)
        chest1.addItem(orangeKey)

        val chest2 = Chest()
        chest2.key = (whiteKey)
        chest2.addItem(yellowKey)
        chest2.addItem(pinkKey)

        val seller1 = Seller()
        seller1.addItem(ledFlash)
        seller1.addItem(blackKey)

        val door1 = Door()
        door1.lock()
        door1.key = (greyKey)

        val door2 = Door()
        door2.key = (greenKey)

        val door3 = Door()
        door3.lock()
        door3.key = (orangeKey)

        val door4 = Door()
        door4.lock()
        door4.key = (blackKey)

        val room1 = Room()
        room1.addLights()
        room1.northWall = (plainWall1)
        room1.westWall = (chest1)
        room1.southWall = (door1)
        room1.eastWall = (mirror1)

        val room2 = Room()
        room2.addLights()
        room2.turnLightButton()
        room2.northWall = (door1)
        room2.westWall = (seller1)
        room2.southWall = (plainWall2)
        room2.eastWall = (door2)

        val room3 = Room()
        room3.northWall = (mirror2)
        room3.westWall = (door2)
        room3.southWall = (door4)
        room3.eastWall = (door3)

        val room4 = Room()
        room4.westWall = (door3)
        room4.addAsExitPoint()

        val room5 = Room()
        room5.addLights()
        room5.turnLightButton()
        room5.northWall = (door4)
        room5.westWall = (chest2)
        room5.southWall = (monster1)
        room5.eastWall = (plainWall2)

        val game = Game()
        game.addItem(greyKey)
        game.addItem(greenKey)
        game.addItem(orangeKey)
        game.addItem(blackKey)
        game.addItem(pinkKey)
        game.addItem(purpleKey)
        game.addItem(whiteKey)
        game.addItem(yellowKey)
        game.addItem(ledFlash)
        game.addRoom(room1)
        game.addRoom(room2)
        game.addRoom(room3)
        game.addRoom(room4)
        game.addRoom(room5)
        game.addDoor(door1, room1, room2)
        game.addDoor(door2, room2, room3)
        game.addDoor(door3, room3, room4)
        game.addDoor(door4, room3, room5)

        val scanner = Scanner(System.`in`)
        while (true) {
            val command = scanner.nextLine()
            game.readCommands(command)
        }
    }
}

