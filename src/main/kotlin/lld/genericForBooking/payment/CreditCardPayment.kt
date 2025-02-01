package lld.genericForBooking.payment

class CreditCardPayment(
    private val cardNumber: String,
    private val cardHolderName: String
) : PaymentMethod {

    override fun processPayment(amount: Double): Payment {
        // Simulate credit card processing logic
        println("Processing credit card payment of $$amount for $cardHolderName (Card: $cardNumber)")
        // Simulate success
        return true
    }
}
