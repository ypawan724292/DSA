package lld.solutions.splitwise.splittype

import lld.solutions.splitwise.User


abstract class Split(protected var user: User?) {
    protected var amount: Double = 0.0

    abstract fun getAmount(): Double

    open fun setAmount(amount: Double) {
        this.amount = amount
    }

    fun getUser(): User? {
        return user
    }
}
