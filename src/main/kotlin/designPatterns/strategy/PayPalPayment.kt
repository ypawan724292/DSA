package designPatterns.strategy

internal class PayPalPayment(private val emailId: String?, private val password: String?) : PaymentStrategy {
    override fun pay(amount: Int) {
        println(amount.toString() + " paid using PayPal")
    }
}