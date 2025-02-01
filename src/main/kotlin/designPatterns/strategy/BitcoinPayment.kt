package designPatterns.strategy

internal class BitcoinPayment(private val bitcoinAddress: String?) : PaymentStrategy {
    override fun pay(amount: Int) {
        println(amount.toString() + " paid using Bitcoin")
    }
}
