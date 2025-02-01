package lld.solutions.onlinestockbrokeragesystem

object StockBrokerageSystemDemo {
    fun run() {
        val stockBroker: StockBroker = StockBroker.getInstance()

        // Create user and account
        val user = User("U001", "John Doe", "john@example.com")
        stockBroker.createAccount(user, 10000.0)
        val account = stockBroker.getAccount("A001")

        // Add stocks to the stock broker
        val stock1 = Stock("AAPL", "Apple Inc.", 150.0)
        val stock2 = Stock("GOOGL", "Alphabet Inc.", 2000.0)
        stockBroker.addStock(stock1)
        stockBroker.addStock(stock2)

        // Place buy orders
        val buyOrder1: Order = BuyOrder("O001", account, stock1, 10, 150.0)
        val buyOrder2: Order = BuyOrder("O002", account, stock2, 5, 2000.0)
        stockBroker.placeOrder(buyOrder1)
        stockBroker.placeOrder(buyOrder2)

        // Place sell orders
        val sellOrder1: Order = SellOrder("O003", account, stock1, 5, 160.0)
        stockBroker.placeOrder(sellOrder1)

        // Print account balance and portfolio
        println("Account Balance: $" + account.balance)
        println("Portfolio: " + account.portfolio.holdings)
    }
}
