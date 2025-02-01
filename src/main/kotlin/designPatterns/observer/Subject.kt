package designPatterns.observer

internal interface Subject {
    fun attach(observer: Observer?)
    fun detach(observer: Observer?)
    fun notifyObservers()
}