package lld.solutions.airlinemanagementsystem.booking

import lld.solutions.airlinemanagementsystem.Passenger
import lld.solutions.airlinemanagementsystem.flight.Flight
import lld.solutions.airlinemanagementsystem.seat.Seat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.atomic.AtomicInteger

class BookingManager private constructor() {
    private val bookings = mutableMapOf<String, Booking>()
    private val lock = Any()
    private val bookingCounter = AtomicInteger(0)

    fun createBooking(flight: Flight, passenger: Passenger, seat: Seat, price: Double): Booking {
        val bookingNumber = generateBookingNumber()
        val booking = Booking(bookingNumber, flight, passenger, seat, price)
        synchronized(lock) {
            bookings.put(bookingNumber, booking)
            booking.confirmBooking()
        }
        return booking
    }

    fun cancelBooking(bookingNumber: String) {
        synchronized(lock) {
            val booking = bookings[bookingNumber]!!
            booking.cancelBooking()
        }
    }

    private fun generateBookingNumber(): String {
        val bookingId = bookingCounter.incrementAndGet()
        val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
        return "BKG" + timestamp + String.format("%06d", bookingId)
    }

    companion object {
        @get:Synchronized
        var instance: BookingManager? = null
            get() {
                if (field == null) {
                    field = BookingManager()
                }
                return field
            }
            private set
    }
}
