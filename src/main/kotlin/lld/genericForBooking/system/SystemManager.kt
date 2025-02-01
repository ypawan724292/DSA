package lld.genericForBooking.system

import lld.genericForBooking.booking.BookingService
import lld.genericForBooking.booking.User
import lld.genericForBooking.cart.CartService
import lld.genericForBooking.delivery.DeliveryService
import lld.genericForBooking.inventory.InventoryItem
import lld.genericForBooking.inventory.InventoryService
import lld.genericForBooking.notification.EmailNotificationService
import lld.genericForBooking.observer.ObserverService
import lld.genericForBooking.payment.PaymentService

class SystemManager() {

    private val observerService = ObserverService()
    private val notificationService = EmailNotificationService()

    private val inventoryService = InventoryService(observerService)
    private val paymentService = PaymentService(notificationService)

    private val deliveryService = DeliveryService()
    private val cartService = CartService()
    private val bookingService = BookingService(inventoryService, paymentService, deliveryService)

    fun createBookingAndPay(item: InventoryItem, user: User): Boolean {
        return bookingService.createBooking(user, listOf(item))
    }

    fun getInventoryItems() {
        inventoryService.listAllItems()
    }

    fun searchItemById(itemId: String): List<InventoryItem> {
        return inventoryService.searchItem(Pair("itemId", itemId))
    }

    fun addToCart(item: InventoryItem) {
        cartService.addItem(item)
    }

    fun book(user: User) {
        bookingService.createBooking(user, cartService.getItems())
    }
}