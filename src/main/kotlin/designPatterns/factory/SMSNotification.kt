package designPatterns.factory
class SMSNotification : Notification {
    override fun notifyUser() {
        println("Sending an SMS Notification")
    }
}