package lld.genericForBooking.booking

import lld.genericForBooking.inventory.InventoryItem

interface BookingSystem {
    fun createBooking(user: User, item: List<InventoryItem>): Boolean
    fun cancelBooking(bookingId: String)
}
