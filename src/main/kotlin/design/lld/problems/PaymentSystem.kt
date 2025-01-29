package design.lld.problems

/**
 * Functional Requirements
 * Users can add money to their account using a payment gateway.
 * Users can make payments to services or merchants.
 * Users can view transaction history (successful, failed).
 * System supports multiple payment methods (Credit/Debit, UPI, Wallet).
 * Transaction fees can be applied to payments.
 * Refund functionality for failed payments.
 */

import java.util.UUID

enum class MethodType { CREDIT_CARD, DEBIT_CARD, UPI, WALLET }
enum class TransactionStatus { PENDING, SUCCESS, FAILED }

data class PaymentUser(val id: Int, val name: String, var balance: Double) {
    fun updateBalance(amount: Double) {
        balance += amount
    }
}

data class PaymentMethod(val methodType: MethodType, val details: String)

data class Transaction(
    val id: String = UUID.randomUUID().toString(),
    val amount: Double,
    val user: PaymentUser,
    var status: TransactionStatus,
    val paymentMethod: PaymentMethod
)

class PaymentService {
    private val users = mutableListOf<PaymentUser>()
    private val transactions = mutableListOf<Transaction>()

    fun addMoney(user: PaymentUser, amount: Double): Boolean {
        if (amount <= 0) {
            println("Invalid amount.")
            return false
        }
        user.updateBalance(amount)
        println("Added $amount to ${user.name}'s account.")
        return true
    }

    fun makePayment(user: PaymentUser, amount: Double, method: PaymentMethod): Transaction {
        if (amount > user.balance) {
            println("Insufficient funds!")
            return Transaction(amount = amount, user = user, status = TransactionStatus.FAILED, paymentMethod = method)
        }

        val transaction =
            Transaction(amount = amount, user = user, status = TransactionStatus.PENDING, paymentMethod = method)
        user.updateBalance(-amount)
        transactions.add(transaction)
        println("Payment of $amount initiated using ${method.methodType}.")

        // Simulate success or failure
        val isSuccess = (1..10).random() > 2 // 80% chance of success
        if (isSuccess) {
            transaction.status = TransactionStatus.SUCCESS
            println("Payment of $amount completed successfully!")
        } else {
            transaction.status = TransactionStatus.FAILED
            println("Payment failed!")
        }
        return transaction
    }

    fun refundTransaction(transaction: Transaction): Boolean {
        if (transaction.status != TransactionStatus.FAILED) {
            println("Refund not applicable for successful transactions.")
            return false
        }

        transaction.user.updateBalance(transaction.amount)
        println("Refunded ${transaction.amount} to ${transaction.user.name}.")
        return true
    }
}

fun main() {
    val user = PaymentUser(1, "Pavan", 1000.0)
    val paymentService = PaymentService()

    // Add money to account
    paymentService.addMoney(user, 500.0)

    // Make a payment using a wallet
    val paymentMethod = PaymentMethod(MethodType.WALLET, "User Wallet")
    val transaction = paymentService.makePayment(user, 800.0, paymentMethod)

    // Refund the failed transaction
    if (transaction.status == TransactionStatus.FAILED) {
        paymentService.refundTransaction(transaction)
    }

    // Make another successful payment
    val successfulTransaction = paymentService.makePayment(user, 300.0, paymentMethod)
}
