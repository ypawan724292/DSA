package designPatterns.strategy

internal interface PaymentStrategy {
    fun pay(amount: Int)
}