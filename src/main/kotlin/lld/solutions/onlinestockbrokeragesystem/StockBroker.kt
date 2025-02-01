package lld.solutions.onlinestockbrokeragesystem

import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.atomic.AtomicInteger

class StockBroker private constructor() {
    private val accounts: MutableMap<String, Account> = ConcurrentHashMap<String, Account>()
    private val stocks: MutableMap<String, Stock> = ConcurrentHashMap<String, Stock>()
    private val orderQueue: Queue<Order> = ConcurrentLinkedQueue<Order>()
    private val accountIdCounter: AtomicInteger = AtomicInteger(1)

    fun createAccount(user: User, initialBalance: Double) {
        val accountId = generateAccountId()
        val account = Account(accountId, user, initialBalance)
        accounts.put(accountId, account)
    }

    fun getAccount(accountId: String): Account {
        return accounts[accountId]!!
    }

    fun addStock(stock: Stock) {
        stocks.put(stock.symbol, stock)
    }

    fun getStock(symbol: String): Stock? {
        return stocks[symbol]
    }

    fun placeOrder(order: Order?) {
        orderQueue.offer(order)
        processOrders()
    }

    private fun processOrders() {
        while (!orderQueue.isEmpty()) {
            val order = orderQueue.poll()
            try {
                order.execute()
            } catch (e: InsufficientFundsException) {
                // Handle exception and notify user
                println("Order failed: " + e.message)
            } catch (e: InsufficientStockException) {
                println("Order failed: " + e.message)
            }
        }
    }

    private fun generateAccountId(): String {
        val accountId = accountIdCounter.getAndIncrement()
        return "A" + String.format("%03d", accountId)
    }

    companion object {
        @get:Synchronized
        var instance: StockBroker? = null
        fun getInstance(): StockBroker {
            if (instance == null) {
                instance = StockBroker()
            }
            return instance!!
        }
    }
}
