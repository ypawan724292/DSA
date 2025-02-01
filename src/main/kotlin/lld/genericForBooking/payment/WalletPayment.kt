package lld.genericForBooking.payment

class WalletPayment(private val walletBalance: Double) : PaymentMethod {
    override fun processPayment(amount: Double): Boolean {
        return if (walletBalance >= amount) {
            println("Processing wallet payment of $$amount from balance: $$walletBalance")
            true
        } else {
            println("❌ Insufficient funds in wallet for payment of $$amount")
            false
        }
    }
}
