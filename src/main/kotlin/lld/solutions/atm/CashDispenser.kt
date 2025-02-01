package lld.solutions.atm

class CashDispenser(private var cashAvailable: Int) {

    @Synchronized
    fun dispenseCash(amount: Int) {
        require(amount <= cashAvailable) { "Insufficient cash available in the ATM." }
        cashAvailable -= amount
        println("Cash dispensed: " + amount)
    }
}
