package designPatterns.builder

internal class Pizza {
    private var dough = ""
    private var sauce: String? = ""
    private var topping: String? = ""

    fun setDough(dough: String) {
        this.dough = dough
    }

    fun setSauce(sauce: String?) {
        this.sauce = sauce
    }

    fun setTopping(topping: String?) {
        this.topping = topping
    }

    override fun toString(): String {
        return "Pizza with " + dough + " dough, " + sauce + " sauce, and " + topping + " topping."
    }
}