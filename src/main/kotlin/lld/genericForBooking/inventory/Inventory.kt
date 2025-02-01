package lld.genericForBooking.inventory

interface Inventory {
    fun addItem(item: InventoryItem)
    fun getItem(itemId: String): InventoryItem?
    fun updateItem(itemId: String, quantity: Int, price: Double)
    fun deleteItem(itemId: String)
    fun listAllItems(): List<InventoryItem>
    fun checkAvailability(itemId: String): Boolean
    fun reserveItem(itemId: String)
    fun releaseItem(itemId: String)
    fun searchItem(vararg filters: Pair<String, Any?>): List<InventoryItem>
    fun notifyItem(item: InventoryItem)
}