package helpers

class Player {
    var gold = 300
        private set
    private val playerItems: MutableList<Item> = ArrayList()

    fun increaseGoldAmount(amount: Int) {
        gold += amount
    }

    fun decreaseGoldAmount(amount: Int) {
        gold -= amount
    }

    fun addItem(item: Item) {
        playerItems.add(item)
    }

    fun removeItem(item: Item) {
        playerItems.remove(item)
    }

    fun hasItem(item: Item): Boolean {
        return playerItems.contains(item)
    }

    fun printAllItems() {
        if (playerItems.size < 1) {
            println("None")
        } else {
            for (i in playerItems.indices) {
                print(playerItems[i].name.toString() + " ")
            }
            println()
        }
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("Gold Amount ").append(gold).append("\n")
        sb.append("Items : ")
        for (item in playerItems) sb.append(item).append("\n")
        return sb.toString()
    }
}
