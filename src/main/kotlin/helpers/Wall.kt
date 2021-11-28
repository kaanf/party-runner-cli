package helpers

abstract class Wall {
    open val name: String? = null
    abstract fun display()
}