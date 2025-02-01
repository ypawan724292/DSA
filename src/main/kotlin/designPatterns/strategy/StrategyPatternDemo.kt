package designPatterns.strategy

/**
 * The Strategy Design Pattern is a behavioral design pattern that defines a family of algorithms, encapsulates each one, and makes them interchangeable. This pattern allows the algorithm to vary independently from clients that use it.
 * Key Components:
 * Strategy: The interface that defines a method for the algorithm.
 * Concrete Strategy: Classes that implement the Strategy interface with specific algorithms.
 * Context: The class that uses a Strategy to execute the algorithm.
 */
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
