package designPatterns.factory

class PushNotification : Notification {
    override fun notifyUser() {
        println("Sending a Push Notification")
    }
}