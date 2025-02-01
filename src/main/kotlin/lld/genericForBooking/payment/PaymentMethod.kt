package lld.genericForBooking.payment

interface PaymentMethod {
    fun processPayment(amount: Double): Boolean
}