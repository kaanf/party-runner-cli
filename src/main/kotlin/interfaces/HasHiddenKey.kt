package interfaces

import helpers.Item

interface HasHiddenKey {
    var hiddenKey: Item?
    fun hasHiddenKey(): Boolean
}