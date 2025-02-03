package lld.solutions.splitwise

import java.util.concurrent.CopyOnWriteArrayList

class Group(val id: String?, val name: String?) {
    val members: MutableList<User?>
    val expenses: MutableList<Expense?>

    init {
        this.members = CopyOnWriteArrayList<User?>()
        this.expenses = CopyOnWriteArrayList<Expense?>()
    }

    fun addMember(user: User?) {
        members.add(user)
    }

    fun addExpense(expense: Expense?) {
        expenses.add(expense)
    }
}
