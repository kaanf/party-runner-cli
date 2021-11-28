package helpers

import interfaces.HasItems

class Seller : Wall(), HasItems {
    override val name = "Seller"
    private val sellerItems: MutableList<Item> = ArrayList()
    override fun display() {
        println("$name --> try \"TRADE - LIST - BUY ITEMNAME - SELL ITEMNAME\"  commands")
    }

    override fun getItems(): MutableList<Item> {
        return sellerItems
    }

    override fun addItem(item: Item) {
        sellerItems.add(item)
    }

    fun listItems() {
        if (sellerItems.size > 0) {
            println(" Item         Price")
            for (item in sellerItems) {
                println(item.name + "   : " + item.price)
            }
        } else {
            println("no items found")
        }
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("Wall type : ").append(name).append("\n")
        sb.append("Items : ")
        for (item in sellerItems) sb.append(item).append("\n")
        return sb.toString()
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun equals(obj: Any?): Boolean {
        return super.equals(obj)
    }
}
