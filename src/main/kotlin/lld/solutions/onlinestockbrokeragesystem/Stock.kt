package lld.solutions.onlinestockbrokeragesystem

data class Stock(
    val symbol: String,
    val name: String,
    var price: Double
) {
    @Synchronized
    fun updatePrice(newPrice: Double) {
        price = newPrice
    }
}
