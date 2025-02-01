package lld.genericForBooking.cart

import lld.genericForBooking.inventory.InventoryItem

class CartService {
    private val items = mutableListOf<InventoryItem>()

    fun addItem(item: InventoryItem) {
        items.add(item)
    }

    fun removeItem(item: InventoryItem) {
        items.remove(item)
    }

    fun getItems(): List<InventoryItem> {
        return items
    }
}