package lld.solutions.airlinemanagementsystem.payment

data class Payment(
    private val paymentId: String?,
    private val paymentMethod: String?,
    private val amount: Double,
    private var status: PaymentStatus?
) {
    fun processPayment() {
        status = PaymentStatus.COMPLETED
    }
}



