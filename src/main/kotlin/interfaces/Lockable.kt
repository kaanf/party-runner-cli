package interfaces

import helpers.Item
import helpers.Key

interface Lockable {
    var key: Item

    fun lock()

    fun isLocked(): Boolean

    fun open()

    fun close()

    fun isValidKey(key: Key): Boolean

    fun useKey(key: Key)
}