package lld.genericForBooking.observer

import lld.genericForBooking.inventory.InventoryItem

class ObserverService() : Subject {

    private val observers = mutableListOf<Observer>()

    // Add an observer to the list
    override fun addObserver(observer: Observer) {
        observers.add(observer)
    }

    // Remove an observer from the list
    override fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

    // Notify all observers about item availability
    override fun notifyObservers(item: InventoryItem) {
        observers.forEach { it.update(item) }
    }
}
