package designPatterns.state

internal class VendingMachine {
    private var currentState: State

    init {
        currentState = NoMoneyState()
    }

    fun setState(state: State) {
        this.currentState = state
    }

    fun insertDollar() {
        currentState.insertDollar(this)
    }

    fun ejectMoney() {
        currentState.ejectMoney(this)
    }

    fun dispense() {
        currentState.dispense(this)
    }
}