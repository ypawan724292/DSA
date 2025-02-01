package lld.solutions.atm

class DepositTransaction(transactionId: String, account: Account, amount: Double) :
    Transaction(transactionId, account, amount) {

    override fun execute() {
        account.credit(amount)
    }
}
