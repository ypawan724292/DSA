package design.lld.problems

/**
 * Food Delivery System - LLD
 *
 *
 * Functional Requirements
 * Users can order food from restaurants.
 * Restaurants can add/remove menu items.
 * Delivery system assigns a delivery person.
 * Users can track their order status.
 */

enum class OrderStatus { PLACED, PREPARING, ON_THE_WAY, DELIVERED }

data class MenuItem(val name: String, val price: Double)

class Restaurant(val name: String) {
    private val menu = mutableListOf<MenuItem>()

    fun addMenuItem(item: MenuItem) { menu.add(item) }
    fun removeMenuItem(item: MenuItem) { menu.remove(item) }
    fun getMenu(): List<MenuItem> = menu
}

data class Order(val user: PaymentUser, val restaurant: Restaurant, val items: List<MenuItem>, var status: OrderStatus = OrderStatus.PLACED)

class FoodDeliveryService {
    private val orders = mutableListOf<Order>()

    fun placeOrder(order: Order): Order {
        orders.add(order)
        return order
    }
}

fun main() {
    val restaurant = Restaurant("Pizza Hut")
    restaurant.addMenuItem(MenuItem("Pepperoni Pizza", 10.0))
    val user = PaymentUser(1, "Pavan")

    val order = Order(user, restaurant, restaurant.getMenu())
    val service = FoodDeliveryService()
    service.placeOrder(order)

    println("Order placed: $order")
}
