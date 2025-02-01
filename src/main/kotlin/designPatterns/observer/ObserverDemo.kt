package designPatterns.observer

object ObserverDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        val weatherStation = WeatherStation()

        val phoneDisplay = TemperatureDisplay("Phone")
        val laptopDisplay = TemperatureDisplay("Laptop")

        weatherStation.attach(phoneDisplay)
        weatherStation.attach(laptopDisplay)

        println("Weather station setting temperature to 25.0 degrees Celsius")
        weatherStation.setTemperature(25.0f)

        println("\nWeather station setting temperature to 30.5 degrees Celsius")
        weatherStation.setTemperature(30.5f)

        weatherStation.detach(laptopDisplay)

        println("\nWeather station setting temperature to 20.0 degrees Celsius")
        weatherStation.setTemperature(20.0f)
    }
}