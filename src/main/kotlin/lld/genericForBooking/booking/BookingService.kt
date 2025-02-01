package lld.genericForBooking.booking

import lld.genericForBooking.delivery.DeliveryService
import lld.genericForBooking.inventory.InventoryItem
import lld.genericForBooking.inventory.InventoryService
import lld.genericForBooking.payment.CreditCardPayment
import lld.genericForBooking.payment.PaymentService
import lld.genericForBooking.payment.PaymentStatus
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

class BookingService(
    private val inventoryService: InventoryService,
    private val paymentService: PaymentService,
    private val deliveryService: DeliveryService
) : BookingSystem {

    private val bookings = ConcurrentHashMap<String, Booking>()

    override fun createBooking(user: User, list: List<InventoryItem>): Boolean {
        list.forEach {
            inventoryService.reserveItem(it.itemId)
        }

        val creditCardPayment = CreditCardPayment("123424", "XYZ")

        val totalAmount = list.sumBy { it.price.toInt() }
        val payment = paymentService.processPayment(creditCardPayment, totalAmount.toDouble(), user)
        if (payment.status != PaymentStatus.SUCCESS) {
            println("❌ Payment failed: $payment")
            list.forEach {
                inventoryService.releaseItem(it.itemId)
            }
            return false
        }
        val bookingId = UUID.randomUUID().toString()

        val booking = Booking(bookingId, payment, user, list, BookingStatus.CONFIRMED)
        bookings[bookingId] = booking
        deliveryService.assignDelivery(list, user)
        println("✅ Booking confirmed: $bookingId for ${user.name}")
        return true
    }

    override fun cancelBooking(bookingId: String) {
        bookings[bookingId]?.let { booking ->
            booking.status = BookingStatus.CANCELLED
            booking.list.forEach {
                inventoryService.releaseItem(it.itemId)
            }
            println("🔄 Booking cancelled: $bookingId for ${booking.user.name}")
        } ?: println("❌ Booking not found: $bookingId")
    }
}
