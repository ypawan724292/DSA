package lld.genericForBooking.observer

import lld.genericForBooking.inventory.InventoryItem

interface Subject {
    fun addObserver(observer: Observer)
    fun removeObserver(observer: Observer)
    fun notifyObservers(item: InventoryItem)
}
