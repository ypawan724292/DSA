package designPatterns.observer

internal class TemperatureDisplay(private val name: String?) : Observer {
    override fun update(temperature: Float) {
        println("$name displays Temperature: $temperature degrees Celsius")
    }
}
