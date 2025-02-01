package lld.genericForBooking.inventory

data class InventoryItem(
    val itemId: String,
    val name: String,
    val loc: String,
    @Volatile var quantity: Int,  // Ensures visibility across threads
    @Volatile var price: Double,   // Ensures visibility across threads
    var status: InventoryStatus = InventoryStatus.AVAILABLE
)

enum class InventoryStatus {
    AVAILABLE, RESERVED, NOT_AVAILABLE
}

