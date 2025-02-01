package lld.genericForBooking.inventory

import lld.genericForBooking.observer.ObserverService
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.locks.ReentrantReadWriteLock

class InventoryService(
    private val observerService: ObserverService
) : Inventory {

    private val inventory = ConcurrentHashMap<String, InventoryItem>()
    private val lock = ReentrantReadWriteLock()

    override fun addItem(item: InventoryItem) {
        lock.writeLock().lock()
        try {
            if (inventory.containsKey(item.itemId)) {
                throw IllegalArgumentException("Item with ID ${item.itemId} already exists.")
            }
            inventory[item.itemId] = item
            println("✅ Item '${item.name}' added successfully!")
        } finally {
            lock.writeLock().unlock()
        }
    }

    override fun getItem(itemId: String): InventoryItem? {
        lock.readLock().lock()
        return try {
            inventory[itemId] ?: throw NoSuchElementException("❌ Item ID $itemId not found.")
        } finally {
            lock.readLock().unlock()
        }
    }

    override fun updateItem(itemId: String, quantity: Int, price: Double) {
        lock.writeLock().lock()
        try {
            val item = inventory[itemId] ?: throw NoSuchElementException("❌ Item ID $itemId not found.")
            item.quantity = quantity
            item.price = price
            item.status = if (quantity > 0) InventoryStatus.AVAILABLE else InventoryStatus.NOT_AVAILABLE
            println("✅ Item '${item.name}' updated successfully!")
        } finally {
            lock.writeLock().unlock()
        }
    }

    override fun deleteItem(itemId: String) {
        lock.writeLock().lock()
        try {
            if (inventory.remove(itemId) != null) {
                println("✅ Item ID $itemId deleted successfully!")
            } else {
                throw NoSuchElementException("❌ Item ID $itemId not found.")
            }
        } finally {
            lock.writeLock().unlock()
        }
    }

    override fun listAllItems(): List<InventoryItem> {
        lock.readLock().lock()
        return try {
            inventory.values.toList()
        } finally {
            lock.readLock().unlock()
        }
    }

    override fun checkAvailability(itemId: String): Boolean {
        lock.readLock().lock()
        return try {
            inventory[itemId]?.quantity ?: 0 > 0
        } finally {
            lock.readLock().unlock()
        }
    }

    override fun reserveItem(itemId: String) {
        lock.writeLock().lock()
        try {
            val item = inventory[itemId] ?: throw NoSuchElementException("❌ Item ID $itemId not found.")
            if (item.quantity > 0) {
                item.quantity -= 1
                if (item.quantity == 0) item.status = InventoryStatus.NOT_AVAILABLE
                println("📦 Reserved '${item.name}'. Remaining: ${item.quantity}")
            } else {
                throw IllegalStateException("⚠️ Item '${item.name}' is out of stock.")
            }
        } finally {
            lock.writeLock().unlock()
        }
    }

    override fun releaseItem(itemId: String) {
        lock.writeLock().lock()
        try {
            val item = inventory[itemId] ?: throw NoSuchElementException("❌ Item ID $itemId not found.")
            item.quantity += 1
            item.status = InventoryStatus.AVAILABLE
            println("🔄 Restocked '${item.name}'. Current stock: ${item.quantity}")
        } finally {
            lock.writeLock().unlock()
        }
    }

    override fun searchItem(vararg filters: Pair<String, Any?>): List<InventoryItem> {
        lock.readLock().lock()
        return try {
            inventory.values.filter { item ->
                filters.all { (key, value) ->
                    when (key.lowercase()) {
                        "name" -> value is String && item.name.contains(value, ignoreCase = true)
                        "minQuantity" -> value is Int && item.quantity >= value
                        "minPrice" -> value is Double && item.price >= value
                        "maxPrice" -> value is Double && item.price <= value
                        "status" -> value is InventoryStatus && item.status == value
                        else -> true
                    }
                }
            }
        } finally {
            lock.readLock().unlock()
        }
    }

    override fun notifyItem(item: InventoryItem) {
        observerService.notifyObservers(item)
    }
}
