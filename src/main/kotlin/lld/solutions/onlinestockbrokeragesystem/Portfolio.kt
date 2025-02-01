package lld.solutions.onlinestockbrokeragesystem

import java.util.concurrent.ConcurrentHashMap

class Portfolio(private val account: Account) {
    val holdings: MutableMap<String?, Int?> = ConcurrentHashMap<String?, Int?>()

    @Synchronized
    fun addStock(stock: Stock, quantity: Int) {
        holdings.put(stock.symbol, holdings.getOrDefault(stock.symbol, 0)!! + quantity)
    }

    @Synchronized
    fun removeStock(stock: Stock, quantity: Int) {
        val symbol = stock.symbol
        if (holdings.containsKey(symbol)) {
            val currentQuantity: Int = holdings.get(symbol)!!
            if (currentQuantity > quantity) {
                holdings.put(symbol, currentQuantity - quantity)
            } else if (currentQuantity == quantity) {
                holdings.remove(symbol)
            } else {
                throw InsufficientStockException("Insufficient stock quantity in the portfolio.")
            }
        } else {
            throw InsufficientStockException("Stock not found in the portfolio.")
        }
    }
}
