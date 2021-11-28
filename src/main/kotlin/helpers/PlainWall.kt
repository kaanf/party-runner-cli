package helpers

class PlainWall : Wall() {
    override val name = "Plain Wall"

    override fun display() {
        println("$name --> try \"A - D\" commands")
    }

    override fun toString(): String {
        return "Wall Type :  $name"
    }
}