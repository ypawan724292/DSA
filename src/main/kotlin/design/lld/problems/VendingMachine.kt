package design.lld.problems

/**
 * Vending Machine - LLD
 * Functional Requirements
 * Users can insert money and select a product.
 * If enough money is inserted, the product is dispensed.
 * If not enough money, machine returns inserted coins.
 * Machine maintains inventory of products.
 * Users can get a refund before making a purchase.
 *
 */


data class Product(val name: String, val price: Double)

class VendingMachine(private val inventory: MutableMap<Product, Int>) {
    private var balance: Double = 0.0

    fun insertMoney(amount: Double) {
        balance += amount
        println("Inserted money: $$amount, Current balance: $$balance")
    }

    fun selectProduct(product: Product): Boolean {
        if (product !in inventory || inventory[product] == 0) {
            println("Product out of stock!")
            return false
        }
        if (balance < product.price) {
            println("Insufficient balance! Please insert more money.")
            return false
        }

        dispenseProduct(product)
        return true
    }

    private fun dispenseProduct(product: Product) {
        inventory[product] = inventory[product]!! - 1
        balance -= product.price
        println("Dispensing ${product.name}. Remaining balance: $$balance")
    }

    fun refund(): Double {
        val refundAmount = balance
        balance = 0.0
        println("Refunding $$refundAmount")
        return refundAmount
    }
}

fun main() {
    val products = mutableMapOf(
        Product("Coke", 1.5) to 5,
        Product("Pepsi", 1.0) to 3,
        Product("Chips", 2.0) to 2
    )

    val vendingMachine = VendingMachine(products)

    vendingMachine.insertMoney(2.0)
}
