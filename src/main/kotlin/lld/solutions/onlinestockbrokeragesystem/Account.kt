package lld.solutions.onlinestockbrokeragesystem

class Account(
    val accountId: String,
    val user: User,
    var balance: Double
) {
    val portfolio: Portfolio = Portfolio(this)

    @Synchronized
    fun deposit(amount: Double) {
        balance += amount
    }

    @Synchronized
    fun withdraw(amount: Double) {
        if (balance >= amount) {
            balance -= amount
        } else {
            throw InsufficientFundsException("Insufficient funds in the account.")
        }
    }
}
