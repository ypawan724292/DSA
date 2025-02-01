package lld.genericForBooking.observer

import lld.genericForBooking.inventory.InventoryItem

class UI2Observer : Observer {
    override fun update(item: InventoryItem) {
        println("UI updated for item: ${item.itemId}")
    }
}