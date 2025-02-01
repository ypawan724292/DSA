package lld.solutions.atm

object Main {
    fun run() {
        val bankingService = BankingService()
        val cashDispenser = CashDispenser(10000)
        val atm = ATM(bankingService, cashDispenser)

        // Create sample accounts
        bankingService.createAccount("1234567890", 1000.0)
        bankingService.createAccount("9876543210", 500.0)

        // Perform ATM operations
        val card = Card("1234567890", "1234")
        atm.authenticateUser(card)

        var balance = atm.checkBalance("1234567890")
        println("Account balance: " + balance)

        atm.withdrawCash("1234567890", 500.0)
        atm.depositCash("9876543210", 200.0)

        balance = atm.checkBalance("1234567890")
        println("Updated account balance: " + balance)
    }
}
