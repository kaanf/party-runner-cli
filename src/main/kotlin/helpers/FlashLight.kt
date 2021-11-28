package helpers

class FlashLight : Item() {
    override lateinit var name: String
    override var price = 0
        set(value) {
            checkPrice(value)
            field = value
        }
    var isTurnedOn = false
        private set

    fun switchLight() {
        isTurnedOn = !isTurnedOn
    }

    override fun checkPrice(price: Int) {
        require(price > 0)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("Item Name : ").append(name).append("\n")
        sb.append("Item Price : ").append(price).append("\n")
        return sb.toString()
    }
}