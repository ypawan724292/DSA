package design.lld.problems

/**
 * Hotel Booking System - LLD
 * Functional Requirements
 * User can search for available rooms based on date & type.
 * User can book a room and make a payment.
 * User can cancel a booking and get a refund.
 * Rooms have different types (Single, Double, Suite).
 * Admin can add/remove rooms.
 * Non-Functional Requirements
 * Scalable and maintainable design.
 * Clean OOP principles (Encapsulation, Abstraction, etc.).
 *
 *
 *
 *
 * Key Design Decisions
 * ✔ Encapsulation: Classes hide internal state (e.g., isAvailable in Room).
 * ✔ Abstraction: Hotel provides only necessary functions (search, book, cancel).
 * ✔ Single Responsibility Principle: Separate classes for Room, User, Booking, Payment.
 * ✔ Scalability: Can easily add more room types, users, payment methods.
 *
 */
enum class RoomType { SINGLE, DOUBLE, SUITE }
enum class PaymentStatus { PENDING, COMPLETED, FAILED }

data class HotelUser(val name: String, val email: String)

data class Payment(val amount: Double, var status: PaymentStatus = PaymentStatus.PENDING)

class Room(val roomNumber: Int, val type: RoomType, val price: Double) {
    var isAvailable: Boolean = true

    fun book(): Boolean {
        return if (isAvailable) {
            isAvailable = false
            true
        } else false
    }

    fun cancelBooking(): Boolean {
        isAvailable = true
        return true
    }
}

data class Booking(val bookingId: Int, val user: HotelUser, val room: Room, val date: String, val payment: Payment)

class Hotel(private val rooms: MutableList<Room>) {
    private val bookings = mutableListOf<Booking>()
    private var bookingCounter = 1

    fun searchRooms(type: RoomType): List<Room> {
        return rooms.filter { it.isAvailable && it.type == type }
    }

    fun bookRoom(user: HotelUser, room: Room, date: String): Booking? {
        if (!room.book()) return null // Room is already booked

        val payment = Payment(room.price, PaymentStatus.COMPLETED) // Simulating payment
        val booking = Booking(bookingCounter++, user, room, date, payment)
        bookings.add(booking)

        return booking
    }

    fun cancelBooking(bookingId: Int): Boolean {
        val booking = bookings.find { it.bookingId == bookingId } ?: return false
        booking.room.cancelBooking()
        bookings.remove(booking)
        return true
    }
}

fun main() {
    val hotel = Hotel(
        mutableListOf(
            Room(101, RoomType.SINGLE, 100.0),
            Room(102, RoomType.DOUBLE, 150.0),
            Room(103, RoomType.SUITE, 250.0)
        )
    )

    val user = HotelUser("Pavan", "pavan@example.com")

    println("Available Rooms: ${hotel.searchRooms(RoomType.SINGLE)}")

    val bookedRoom = hotel.searchRooms(RoomType.SINGLE).firstOrNull()
    if (bookedRoom != null) {
        val booking = hotel.bookRoom(user, bookedRoom, "2025-02-01")
        println("Booking Successful: $booking")

        hotel.cancelBooking(booking!!.bookingId)
        println("Booking Canceled")
    }
}
