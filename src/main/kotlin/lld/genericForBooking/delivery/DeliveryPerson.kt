package lld.genericForBooking.delivery

import lld.genericForBooking.inventory.InventoryItem

data class DeliveryPerson(
    val id: String,
    val name: String,
    val location: String,
    var isAvailable: Boolean,
    var averageRating: Double = 0.0,
    var totalDeliveries: Int = 0
) {
    fun assignDelivery(item: List<InventoryItem>): Boolean {
        // Some logic for assigning the delivery
        return if (isAvailable) {
            isAvailable = false
            println("Delivery person $name assigned to deliver item '${item}'.")
            true
        } else {
            println("$name is not available to deliver '${item}'.")
            false
        }
    }


    fun updateRating(newRating: Double) {
        totalDeliveries++
        averageRating = ((averageRating * (totalDeliveries - 1)) + newRating) / totalDeliveries
    }
}

