package lld.solutions.airlinemanagementsystem.booking

import lld.solutions.airlinemanagementsystem.Passenger
import lld.solutions.airlinemanagementsystem.flight.Flight
import lld.solutions.airlinemanagementsystem.seat.Seat


data class Booking(
    val bookingNumber: String,
    val flight: Flight,
    val passenger: Passenger,
    val seat: Seat,
    val price: Double,
    private var status: BookingStatus? = null
) {

    fun confirmBooking() {
        status = BookingStatus.CONFIRMED
    }

    fun cancelBooking() {
        status = BookingStatus.CANCELLED
    }
}
