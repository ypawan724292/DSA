package designPatterns.factory

interface Subscription {
    fun subscriptionType(): String?
    fun addSubscription(customer: Customer?): Boolean
    fun removeSubscription(customer: Customer?): Boolean
    fun updateSubscription(customer: Customer?): Boolean
}
