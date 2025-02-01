package lld.solutions.atm

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.atomic.AtomicLong

class ATM(
    private val bankingService: BankingService,
    private val cashDispenser: CashDispenser
) {
    fun authenticateUser(card: Card?) {
        // Authenticate user using card and PIN
        // ...
    }

    fun checkBalance(accountNumber: String): Double {
        val account = bankingService.getAccount(accountNumber)
        return account.balance
    }

    fun withdrawCash(accountNumber: String, amount: Double) {
        val account = bankingService.getAccount(accountNumber)
        // Check if sufficient balance is available
        if (account.balance < amount) {
            println("Error: Insufficient balance.")
            return
        }
        val transaction: Transaction = WithdrawalTransaction(generateTransactionId(), account, amount)
        bankingService.processTransaction(transaction)
        cashDispenser.dispenseCash(amount.toInt())
    }

    fun depositCash(accountNumber: String, amount: Double) {
        val account = bankingService.getAccount(accountNumber)
        val transaction: Transaction = DepositTransaction(generateTransactionId(), account, amount)
        bankingService.processTransaction(transaction)
    }

    private fun generateTransactionId(): String {
        // Generate a unique transaction ID
        val transactionNumber: Long = transactionCounter.incrementAndGet()
        val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
        return "TXN" + timestamp + String.format("%010d", transactionNumber)
    }

    companion object {
        private val transactionCounter = AtomicLong(0)
    }
}
