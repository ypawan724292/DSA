package lld.solutions.atm

abstract class Transaction(
    protected val transactionId: String,
    protected val account: Account,
    protected val amount: Double
) {
    abstract fun execute()
}
