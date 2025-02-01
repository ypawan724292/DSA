package lld.solutions.atm

class WithdrawalTransaction(transactionId: String, account: Account, amount: Double) :
    Transaction(transactionId, account, amount) {

    override fun execute() {
        account.debit(amount)
    }
}
