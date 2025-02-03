package lld.solutions.splitwise.splittype

import lld.solutions.splitwise.User

class EqualSplit(user: User?) : Split(user) {
    override fun getAmount(): Double {
        return amount
    }

    override fun setAmount(amount: Double) {
        this.amount = amount
    }
}
