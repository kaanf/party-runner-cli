package helpers

class Room : Cloneable {
    lateinit var northWall: Wall
    lateinit var westWall: Wall
    lateinit var southWall: Wall
    lateinit var eastWall: Wall
    private var flashLightened = false
    private var lights = false
    private var lightButton = false
    var isExitPoint = false
        private set

    fun turnOnFlashlight() {
        flashLightened = true
    }

    private fun lightedByFlashLight(): Boolean {
        return flashLightened
    }

    val isLit: Boolean
        get() = lightsOn() || lightedByFlashLight()

    fun switchLights() {
        if (hasLights()) {
            if (lightsOn()) {
                lightButton = false
                println("Lights turned OFF")
            } else {
                lightButton = true
                println("Lights turned ON")
            }
        } else {
            if (lightedByFlashLight()) {
                println("Room is already lit by FLASHLIGHT")
            } else println("No lights in the room, you should use a FLASHLIGHT")
        }
    }

    fun turnLightButton() {
        if (hasLights()) lightButton = true
    }

    private fun lightsOn(): Boolean {
        return if (hasLights()) lightButton else flashLightened
    }

    private fun hasLights(): Boolean {
        return lights
    }

    fun addLights() {
        lights = true
    }

    fun addAsExitPoint() {
        isExitPoint = true
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("North Wall : ").append(northWall.name).append("\n")
        sb.append("West Wall : ").append(westWall.name).append("\n")
        sb.append("South Wall : ").append(southWall.name).append("\n")
        sb.append("East Wall : ").append(eastWall.name).append("\n")
        sb.append("Is Lit ? ").append(isLit).append("\n")
        return sb.toString()
    }
}