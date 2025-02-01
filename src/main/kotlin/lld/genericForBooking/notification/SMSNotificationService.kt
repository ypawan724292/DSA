package lld.genericForBooking.notification

import lld.genericForBooking.booking.User

class SMSNotificationService : NotificationService {

    override fun sendNotification(user: User, notificationType: NotificationType) {
        val message = when (notificationType) {
            NotificationType.PAYMENT_SUCCESS -> "Your payment of $$${user.phone} was successful!"
            NotificationType.PAYMENT_FAILURE -> "Your payment attempt has failed. Please check your payment method."
            else -> {}
        }
        println("Sending Sms to ${user.phone}: $message")
    }
}