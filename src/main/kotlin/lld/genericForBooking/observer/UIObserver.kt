package lld.genericForBooking.observer

import lld.genericForBooking.inventory.InventoryItem

class UIObserver : Observer {
    override fun update(item: InventoryItem) {
        println("UI updated for item: ${item.itemId}")
    }
}

