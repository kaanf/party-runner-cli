package helpers

import interfaces.HasHiddenKey

class Mirror : Wall(), HasHiddenKey {
    override val name = "Mirror"
    override var hiddenKey: Item? = null

    override fun display() {
        println("$name --> try \"CHECK\"  command")
    }

    override fun hasHiddenKey(): Boolean {
        return hiddenKey != null
    }

    override fun toString(): String {
        return "Wall Type : $name"
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun equals(obj: Any?): Boolean {
        return super.equals(obj)
    }
}