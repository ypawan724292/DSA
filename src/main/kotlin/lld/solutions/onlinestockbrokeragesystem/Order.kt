package lld.solutions.onlinestockbrokeragesystem

abstract class Order(
    protected val orderId: String,
    protected val account: Account,
    protected val stock: Stock,
    protected val quantity: Int,
    protected val price: Double,
    protected var status: OrderStatus = OrderStatus.PENDING
) {

    abstract fun execute()
}
