package lld.solutions.onlinestockbrokeragesystem

class SellOrder(orderId: String?, account: Account?, stock: Stock?, quantity: Int, price: Double) :
    Order(orderId, account, stock, quantity, price) {
    override fun execute() {
        // Check if the user has sufficient quantity of the stock to sell
        // Update portfolio and perform necessary actions
        val totalProceeds = quantity * price
        account.deposit(totalProceeds)
        status = OrderStatus.EXECUTED
    }
}
