package lld.solutions.carrentalsystem.payment

class CreditCardPaymentProcessor : PaymentProcessor {
    override fun processPayment(amount: Double): Boolean {
        // Process credit card payment
        // ...
        return true
    }
}
