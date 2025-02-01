package designPatterns.factory

object NotificationFactory {
    fun createNotification(type: String): Notification {
        return when (type.lowercase()) {
            "email" -> EmailNotification()
            "sms" -> SMSNotification()
            "push" -> PushNotification()
            else -> throw IllegalArgumentException("Unknown notification type")
        }
    }
}
