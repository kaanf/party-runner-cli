package helpers

abstract class Item {
    abstract var name: String
    abstract var price: Int

    abstract fun checkPrice(price: Int)
}