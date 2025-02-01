package lld.genericForBooking.notification

import lld.genericForBooking.booking.User

class EmailNotificationService : NotificationService {

    override fun sendNotification(user: User, notificationType: NotificationType) {
        val message = when (notificationType) {
            NotificationType.PAYMENT_SUCCESS -> "Your payment of $$${user.email} was successful!"
            NotificationType.PAYMENT_FAILURE -> "Your payment attempt has failed. Please check your payment method."
            else -> {}
        }
        println("Sending email to ${user.email}: $message")
    }
}






