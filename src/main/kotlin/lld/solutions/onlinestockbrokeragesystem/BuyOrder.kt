package lld.solutions.onlinestockbrokeragesystem

class BuyOrder(
    orderId: String,
    account: Account,
    stock: Stock,
    quantity: Int,
    price: Double
) : Order(orderId, account, stock, quantity, price) {
    override fun execute() {
        val totalCost = quantity * price
        if (account.balance >= totalCost) {
            account.withdraw(totalCost)
            // Update portfolio and perform necessary actions
            status = OrderStatus.EXECUTED
        } else {
            status = OrderStatus.REJECTED
            throw InsufficientFundsException("Insufficient funds to execute the buy order.")
        }
    }
}
