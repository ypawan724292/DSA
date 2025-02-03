package lld.solutions.splitwise.splittype

import lld.solutions.splitwise.User

class PercentSplit(user: User?, val percent: Double) : Split(user) {
    override fun getAmount(): Double {
        return amount
    }
}
