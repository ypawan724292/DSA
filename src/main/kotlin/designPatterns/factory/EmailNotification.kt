package designPatterns.factory

class EmailNotification : Notification {
    override fun notifyUser() {
        println("Sending an Email Notification")
    }
}


