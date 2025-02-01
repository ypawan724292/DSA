package lld.genericForBooking.delivery

import lld.genericForBooking.booking.User
import lld.genericForBooking.inventory.InventoryItem

class DeliveryService : Delivery {
    private val deliveryPeople = mutableListOf<DeliveryPerson>()
    private val deliveryHistory = mutableListOf<DeliveryHistory>()

    // Add a new delivery person
    override fun addDeliveryPerson(deliveryPerson: DeliveryPerson): Boolean {
        return if (deliveryPeople.any { it.id == deliveryPerson.id }) {
            println("Delivery person with ID ${deliveryPerson.id} already exists.")
            false
        } else {
            deliveryPeople.add(deliveryPerson)
            println("Added delivery person: ${deliveryPerson.name}")
            true
        }
    }

    // Get a delivery person by ID
    override fun getDeliveryPerson(id: String): DeliveryPerson? {
        return deliveryPeople.find { it.id == id }
    }

    // Update an existing delivery person by ID
    override fun updateDeliveryPerson(id: String, updatedDeliveryPerson: DeliveryPerson): Boolean {
        val index = deliveryPeople.indexOfFirst { it.id == id }
        return if (index >= 0) {
            deliveryPeople[index] = updatedDeliveryPerson
            println("Updated delivery person: ${updatedDeliveryPerson.name}")
            true
        } else {
            println("Delivery person with ID $id not found.")
            false
        }
    }

    // Delete a delivery person by ID
    override fun deleteDeliveryPerson(id: String): Boolean {
        val index = deliveryPeople.indexOfFirst { it.id == id }
        return if (index >= 0) {
            deliveryPeople.removeAt(index)
            println("Deleted delivery person with ID: $id")
            true
        } else {
            println("Delivery person with ID $id not found.")
            false
        }
    }

    // Get all delivery people
    override fun getAllDeliveryPeople(): List<DeliveryPerson> {
        return deliveryPeople
    }

    override fun assignDelivery(item: List<InventoryItem>, user: User): DeliveryPerson {
        val deliveryPeopleList = getAllDeliveryPeople()
        // Calculate distances and select the nearest delivery person
        val person =
            deliveryPeopleList.minByOrNull { calculateDistance(it.location, user.location) } ?: deliveryPeople.first()
        person.assignDelivery(item)
        deliveryHistory.add(DeliveryHistory(item, person, 10L))
        return person
    }

    fun calculateDistance(location: String, location1: String): Int {
        //find the distance between two locations
        return location.length - location1.length
    }
}
