package lld.genericForBooking.payment

import lld.genericForBooking.booking.User
import lld.genericForBooking.notification.NotificationService
import lld.genericForBooking.notification.NotificationType
import java.util.concurrent.ConcurrentHashMap

class PaymentService(
    private val notificationService: NotificationService
) {

    private val payments = ConcurrentHashMap<String, Payment>()

    fun processPayment(paymentMethod: PaymentMethod, amount: Double, user: User): Payment {
        val isPaymentSuccessful = paymentMethod.processPayment(amount)
        val paymentStatus = if (isPaymentSuccessful) PaymentStatus.SUCCESS else PaymentStatus.FAILURE

        // Create a Payment entity
        val payment = Payment(
            user = user,
            amount = amount,
            paymentMethod = paymentMethod.javaClass.simpleName,
            status = paymentStatus
        )

        // Optionally store the payment details (for persistence or logging)
        payments[payment.paymentId] = payment

        val notificationType = if (paymentStatus == PaymentStatus.SUCCESS)
            NotificationType.PAYMENT_SUCCESS
        else NotificationType.PAYMENT_FAILURE
        notificationService.sendNotification(user, notificationType)

        return payment
    }

    fun getAllPayments(): List<Payment> {
        return payments.values.toList()
    }

}
