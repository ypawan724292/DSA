package lld.genericForBooking.delivery

import lld.genericForBooking.booking.User
import lld.genericForBooking.inventory.InventoryItem

interface Delivery {
    fun addDeliveryPerson(deliveryPerson: DeliveryPerson): Boolean
    fun getDeliveryPerson(id: String): DeliveryPerson?
    fun updateDeliveryPerson(id: String, updatedDeliveryPerson: DeliveryPerson): Boolean
    fun deleteDeliveryPerson(id: String): Boolean
    fun getAllDeliveryPeople(): List<DeliveryPerson>

    fun assignDelivery(item: List<InventoryItem>, user: User): DeliveryPerson
}
