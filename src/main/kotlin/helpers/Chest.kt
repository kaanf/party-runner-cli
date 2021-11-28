package helpers

import interfaces.HasItems
import interfaces.Lockable

class Chest : Wall(), Lockable, HasItems {
    override var name: String = "Chest"
    private var lockStatus: Boolean = false
    override lateinit var key: Item
    private var chestItems = ArrayList<Item>()

    override fun display() {
        println("$name --> try \"CHECK\" command or try \"USE KEY NAME\" to open and close chest.")
    }

    override fun addItem(item: Item) {
        chestItems.add(item)
    }

    override fun getItems(): MutableList<Item> {
        return chestItems
    }

    override fun lock() {
        lockStatus = true
    }

    override fun isLocked(): Boolean {
        return lockStatus
    }

    override fun open() {
        lockStatus = false
    }

    override fun close() {
        lockStatus = true
    }

    override fun isValidKey(key: Key): Boolean {
        try {
            return key.name == key.name
        } catch (e: Exception) {
            println("Key value is null, key method isn't used yet")
            throw NullPointerException()
        }
    }

    override fun useKey(key: Key) {
        if (isValidKey(key)) {
            if (isLocked()) {
                open()
                println("Chest opened")
            } else {
                close()
                println("Chest closed")
            }
        } else {
            println("Wrong key! required key is : " + key.name)
        }
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("Wall type : ").append(name).append("\n")
        sb.append("Key : ").append(key.name).append("\n")
        sb.append("lock status : ").append(lockStatus).append("\n")
        sb.append("Items : ")
        for (item in chestItems) sb.append(item).append("\n")
        return sb.toString()
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun equals(obj: Any?): Boolean {
        return super.equals(obj)
    }
}