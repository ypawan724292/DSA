package lld.solutions.airlinemanagementsystem.payment

class PaymentProcessor private constructor() {
    fun processPayment(payment: Payment) {
        // Process payment using the selected payment method
        payment.processPayment()
    }

    companion object {
        @get:Synchronized
        var instance: PaymentProcessor? = null
            get() {
                if (field == null) {
                    field = PaymentProcessor()
                }
                return field
            }
            private set
    }
}
