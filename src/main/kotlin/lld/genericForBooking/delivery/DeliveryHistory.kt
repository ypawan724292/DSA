package lld.genericForBooking.delivery

import lld.genericForBooking.inventory.InventoryItem

data class DeliveryHistory(
    val item: List<InventoryItem>,
    val deliveryPerson: DeliveryPerson,
    val deliveryTime: Long
)
