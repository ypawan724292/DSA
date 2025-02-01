package lld.solutions.carrentalsystem.payment

interface PaymentProcessor {
    fun processPayment(amount: Double): Boolean
}
