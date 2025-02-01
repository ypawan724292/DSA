package lld.solutions.carrentalsystem.payment

class PayPalPaymentProcessor : PaymentProcessor {
    override fun processPayment(amount: Double): Boolean {
        // Process PayPal payment
        // ...
        return true
    }
}
