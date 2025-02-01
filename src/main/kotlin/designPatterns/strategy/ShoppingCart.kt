package designPatterns.strategy

internal class ShoppingCart {
    private var paymentStrategy: PaymentStrategy? = null

    fun setPaymentStrategy(strategy: PaymentStrategy) {
        this.paymentStrategy = strategy
    }

    fun checkout(amount: Int) {
        paymentStrategy!!.pay(amount)
    }
}