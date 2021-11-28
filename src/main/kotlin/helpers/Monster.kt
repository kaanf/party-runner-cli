package helpers

import interfaces.HasHiddenKey

class Monster: Wall(), HasHiddenKey {
    override lateinit var name: String
    override var hiddenKey: Item? = null
    override fun hasHiddenKey(): Boolean {
        return hiddenKey != null
    }
    override fun display() {
        println("$name --> try \"ATTACK\" command to collect key.")
    }
}
