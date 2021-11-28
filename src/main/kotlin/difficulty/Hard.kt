package difficulty

import helpers.*
import java.io.IOException
import java.util.*

object Hard {
    @Throws(IOException::class)
    @JvmStatic
    fun main() {
        val blackKey = Key()
        blackKey.name = "Black Key"
        blackKey.price = 99

        val whiteKey = Key()
        whiteKey.name = "White Key"
        whiteKey.price = 115

        val silverKey = Key()
        silverKey.name = "Silver Key"
        silverKey.price = 200

        val bronzeKey = Key()
        bronzeKey.name = "Bronze Key"
        bronzeKey.price = 150

        val goldKey = Key()
        goldKey.name = "Gold Key"
        goldKey.price = 300

        val woodKey = Key()
        woodKey.name = "Wood Key"
        woodKey.price = 170

        val redKey = Key()
        redKey.name = "Red Key"
        redKey.price = 153

        val flash = FlashLight()
        flash.name = "Flash"
        flash.price = 120

        val plainWall1 = PlainWall()
        val plainWall2 = PlainWall()
        val plainWall3 = PlainWall()
        val plainWall4 = PlainWall()

        val mirror1 = Mirror()
        mirror1.hiddenKey = (woodKey)

        val mirror2 = Mirror()

        val monster1 = Monster()
        monster1.hiddenKey = (whiteKey)
        monster1.name = "Scottish Fold Cat"

        val monster2 = Monster()
        monster2.name = "British Shorthair Cat"

        val chest1 = Chest()
        chest1.lock()
        chest1.key = (goldKey)

        val chest2 = Chest()
        chest2.key = (whiteKey)
        chest2.addItem(bronzeKey)

        val seller1 = Seller()
        seller1.addItem(flash)
        seller1.addItem(silverKey)
        seller1.addItem(goldKey)

        val door1 = Door()
        door1.key = (blackKey)

        val door2 = Door()
        door2.lock()
        door2.key = (woodKey)

        val door3 = Door()
        door3.key = (redKey)

        val door4 = Door()
        door4.lock()
        door4.key = (silverKey)

        val door5 = Door()
        door5.lock()
        door5.key = (bronzeKey)

        val room1 = Room()
        room1.addLights()
        room1.northWall = (monster1)
        room1.westWall = (plainWall1)
        room1.southWall = (door1)
        room1.eastWall = (plainWall2)

        val room2 = Room()
        room2.addLights()
        room2.turnLightButton()
        room2.northWall = (door1)
        room2.westWall = (mirror1)
        room2.southWall = (seller1)
        room2.eastWall = (door2)

        val room3 = Room()
        room3.addLights()
        room3.northWall = (door3)
        room3.westWall = (door2)
        room3.southWall = (plainWall3)
        room3.eastWall = (mirror2)

        val room4 = Room()
        room4.addLights()
        room4.turnLightButton()
        room4.northWall = (door5)
        room4.westWall = (chest1)
        room4.southWall = (door3)
        room4.eastWall = (door4)

        val room5 = Room()
        room5.northWall = (monster2)
        room5.westWall = (door4)
        room5.southWall = (chest2)
        room5.eastWall = (plainWall4)

        val room6 = Room()
        room6.addAsExitPoint()
        room6.southWall = (door5)

        val game = Game()
        game.addItem(blackKey)
        game.addItem(whiteKey)
        game.addItem(silverKey)
        game.addItem(bronzeKey)
        game.addItem(goldKey)
        game.addItem(woodKey)
        game.addItem(redKey)
        game.addItem(flash)
        game.addRoom(room1)
        game.addRoom(room2)
        game.addRoom(room3)
        game.addRoom(room4)
        game.addRoom(room5)
        game.addRoom(room6)
        game.addDoor(door1, room1, room2)
        game.addDoor(door2, room2, room3)
        game.addDoor(door3, room3, room4)
        game.addDoor(door4, room4, room5)
        game.addDoor(door5, room4, room6)

        val scanner = Scanner(System.`in`)
        while (true) {
            val command = scanner.nextLine()
            game.readCommands(command)
        }
    }
}

