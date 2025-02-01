package lld.genericForBooking.booking

import lld.genericForBooking.inventory.InventoryItem
import lld.genericForBooking.payment.Payment

data class Booking(
    val bookingId: String,
    val payment: Payment,
    val user: User,
    val list: List<InventoryItem>,
    var status: BookingStatus
)

enum class BookingStatus { CONFIRMED, CANCELLED }