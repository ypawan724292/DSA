package designPatterns.observer

internal class WeatherStation : Subject {
    private val observers: List<Observer?>
    private var temperature = 0f

    init {
        observers = ArrayList()
    }

    fun setTemperature(temperature: Float) {
        this.temperature = temperature
        notifyObservers()
    }

    override fun attach(observer: Observer?) {
        observers.add(observer)
    }

    override fun detach(observer: Observer?) {
        observers.remove(observer)
    }

    override fun notifyObservers() {
        for (observer in observers) {
            observer!!.update(temperature)
        }
    }
}