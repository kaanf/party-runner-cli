package interfaces

import helpers.Item

interface HasItems {
    fun getItems(): MutableList<Item>
    fun addItem(item: Item)
}