package lld.solutions.atm

data class Account(
    val accountNumber: String,
    var balance: Double
) {
    fun debit(amount: Double) {
        balance -= amount
    }

    fun credit(amount: Double) {
        balance += amount
    }
}
