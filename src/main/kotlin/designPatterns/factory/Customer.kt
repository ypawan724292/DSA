package designPatterns.factory

class Customer(var customerId: String?) {
    var subscription: Subscription? = null

    fun SubscribePlan(subscription: Subscription?) {
        this.subscription = subscription
        this.subscription!!.addSubscription(this)
    }

    fun unSubscribePlan() {
        subscription!!.removeSubscription(this)
    }

    fun updateSubcribePlan(newSubscription: Subscription?) {
        subscription!!.removeSubscription(this)
        this.subscription = newSubscription
        this.subscription!!.addSubscription(this)
    }
}
