package lld.solutions.splitwise.splittype

import lld.solutions.splitwise.User

class ExactSplit(user: User?, amount: Double) : Split(user) {
    init {
        this.amount = amount
    }

    override fun getAmount(): Double {
        return amount
    }
}
