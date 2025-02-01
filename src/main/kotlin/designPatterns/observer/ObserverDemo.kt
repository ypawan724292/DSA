package designPatterns.observer

/**
 * The Observer Design Pattern is a behavioral design pattern that defines a one-to-many dependency between objects so
 * that when one object changes state, all its dependents are notified and updated automatically.
 * Key Components:
 * Subject: The object that holds the state and sends notifications to observers.
 * Observer: The interface or abstract class for objects that should be notified of changes in the subject.
 * Concrete Subject: The class that implements the Subject interface and maintains the state of interest.
 * Concrete Observer: The class that implements the Observer interface and updates its state in response to notifications from the subject.
 */
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