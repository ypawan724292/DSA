package lld.solutions.splitwise

import lld.solutions.splitwise.splittype.EqualSplit
import splitwise.splittype.EqualSplit
import java.util.concurrent.ConcurrentHashMap
import kotlin.math.abs

class SplitwiseService private constructor() {
    private val users: MutableMap<String?, User?> = ConcurrentHashMap<String?, User?>()
    private val groups: MutableMap<String?, Group?> = ConcurrentHashMap<String?, Group?>()

    fun addUser(user: User) {
        users.put(user.getId(), user)
    }

    fun addGroup(group: Group) {
        groups.put(group.getId(), group)
    }

    fun addExpense(groupId: String?, expense: Expense) {
        val group = groups.get(groupId)
        if (group != null) {
            group.addExpense(expense)
            splitExpense(expense)
            updateBalances(expense)
        }
    }

    private fun splitExpense(expense: Expense) {
        val totalAmount = expense.getAmount()
        val splits: MutableList<Split> = expense.getSplits()
        val totalSplits = splits.size

        val splitAmount = totalAmount / totalSplits
        for (split in splits) {
            if (split is EqualSplit) {
                split.setAmount(splitAmount)
            } else if (split is PercentSplit) {
                split.setAmount(totalAmount * split.getPercent() / 100.0)
            }
        }
    }

    private fun updateBalances(expense: Expense) {
        for (split in expense.getSplits()) {
            val paidBy = expense.getPaidBy()
            val user: User = split.getUser()
            val amount: Double = split.getAmount()

            if (paidBy != user) {
                updateBalance(paidBy, user, amount)
                updateBalance(user, paidBy, -amount)
            }
        }
    }

    private fun updateBalance(user1: User, user2: User, amount: Double) {
        val key = getBalanceKey(user1, user2)
        user1.getBalances().put(key, user1.getBalances().getOrDefault(key, 0.0) + amount)
    }

    private fun getBalanceKey(user1: User, user2: User): String {
        return user1.getId() + ":" + user2.getId()
    }

    fun settleBalance(userId1: String?, userId2: String?) {
        val user1 = users.get(userId1)
        val user2 = users.get(userId2)

        if (user1 != null && user2 != null) {
            val key = getBalanceKey(user1, user2)
            val balance = user1.getBalances().getOrDefault(key, 0.0)

            if (balance > 0) {
                createTransaction(user1, user2, balance)
                user1.getBalances().put(key, 0.0)
                user2.getBalances().put(getBalanceKey(user2, user1), 0.0)
            } else if (balance < 0) {
                createTransaction(user2, user1, abs(balance))
                user1.getBalances().put(key, 0.0)
                user2.getBalances().put(getBalanceKey(user2, user1), 0.0)
            }
        }
    }

    private fun createTransaction(sender: User?, receiver: User?, amount: Double) {
        val transactionId = generateTransactionId()
        val transaction = Transaction(transactionId, sender, receiver, amount)
        // Process the transaction
        // ...
    }

    private fun generateTransactionId(): String {
        val transactionNumber: Int = SplitwiseService.Companion.transactionCounter.incrementAndGet()
        return SplitwiseService.Companion.TRANSACTION_ID_PREFIX + String.format("%06d", transactionNumber)
    }

    companion object {
        @get:Synchronized
        var instance: SplitwiseService? = null
            get() {
                if (field == null) {
                    field = SplitwiseService()
                }
                return field
            }
            private set
        private const val TRANSACTION_ID_PREFIX = "TXN"
        private val transactionCounter: AtomicInteger = AtomicInteger(0)
    }
}
