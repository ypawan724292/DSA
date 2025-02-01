package designPatterns.strategy

object StrategyPatternDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        val cart = ShoppingCart()

        // Pay by credit card
        cart.setPaymentStrategy(CreditCardPayment("John Doe", "1234567890123456", "786", "12/25"))
        cart.checkout(100)

        // Pay by PayPal
        cart.setPaymentStrategy(PayPalPayment("johndoe@example.com", "mypwd"))
        cart.checkout(200)

        // Pay by Bitcoin
        cart.setPaymentStrategy(BitcoinPayment("1BvBMSEYstWetqTFn5Au4m4GFg7xJaNVN2"))
        cart.checkout(300)
    }
}
