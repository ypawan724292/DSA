package lld.genericForBooking.notification

import lld.genericForBooking.booking.User

interface NotificationService {
    fun sendNotification(user: User, notificationType: NotificationType)
}


enum class NotificationType {
    PAYMENT_SUCCESS,
    PAYMENT_FAILURE,
    ITEM_AVAILABLE
}
