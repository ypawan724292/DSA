package lld.genericForBooking.observer

import lld.genericForBooking.inventory.InventoryItem

interface Observer {
    fun update(item: InventoryItem)
}