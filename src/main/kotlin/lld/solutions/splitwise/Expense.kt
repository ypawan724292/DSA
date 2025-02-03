package lld.solutions.splitwise

import lld.solutions.splitwise.splittype.Split

class Expense(val id: String, val amount: Double, val description: String, val paidBy: User) {
    private val splits: MutableList<Split> = ArrayList<Split>()

    fun addSplit(split: Split) {
        splits.add(split)
    }

    fun getSplits(): MutableList<Split> {
        return splits
    }
}
