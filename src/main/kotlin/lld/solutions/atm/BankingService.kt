package lld.solutions.atm


class BankingService {

    private val accounts = mutableMapOf<String, Account>()

    fun createAccount(accountNumber: String, initialBalance: Double) {
        accounts.put(accountNumber, Account(accountNumber, initialBalance))
    }

    fun getAccount(accountNumber: String): Account {
        val account = accounts[accountNumber]
        if (account == null) {
            throw Exception("Account not found")
        }
        return account
    }

    fun processTransaction(transaction: Transaction) {
        transaction.execute()
    }

    fun checkAccountExists(accountNumber: String): Boolean {
        return accounts.containsKey(accountNumber)
    }
}
