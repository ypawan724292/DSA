package designPatterns.command

internal class Light(private val location: String?) {
    private var isOn = false

    fun turnOn() {
        isOn = true
        println(location + " light is now ON")
    }

    fun turnOff() {
        isOn = false
        println(location + " light is now OFF")
    }
}