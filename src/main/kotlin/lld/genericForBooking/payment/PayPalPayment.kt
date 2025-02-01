package lld.genericForBooking.payment

class PayPalPayment(private val email: String) : PaymentMethod {
    override fun processPayment(amount: Double): Boolean {
        // Simulate PayPal processing logic
        println("Processing PayPal payment of $$amount for $email")
        // Simulate success
        return true
    }
}
