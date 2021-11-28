package helpers

import interfaces.Lockable

class Door : Wall(), Lockable {
    override var name = "Door"
    private var lockStatus: Boolean = false
    override lateinit var key: Item

    override fun display() {
        println("$name --> try \"CHECK - OPEN\" commands || try \"USE KEYNAME (KEY NAME)\" (to open/close the door)")
    }

    override fun isLocked(): Boolean {
        return lockStatus
    }

    override fun lock() {
        lockStatus = true
    }

    override fun open() {
        lockStatus = false
    }

    override fun close() {
        lockStatus = true
    }

    override fun isValidKey(key: Key): Boolean {
        return try {
            key.name.equals(key.name)
        } catch (e: Exception) {
            println("Key value is null , key method isn't used yet")
            throw NullPointerException()
        }
    }

    override fun useKey(key: Key) {
        if (isValidKey(key)) {
            if (isLocked()) {
                open()
                println("Door opened use W command to move to adjacent room")
            } else {
                close()
                println("Door closed")
            }
        } else {
            println("Wrong key! required key is : " + key.name)
        }
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("Wall type : ").append(this.name).append("\n")
        sb.append("Key : ").append(this.key.name).append("\n")
        sb.append("lock status : ").append(this.lockStatus).append("\n")
        return sb.toString()
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun equals(obj: Any?): Boolean {
        return super.equals(obj)
    }
}
