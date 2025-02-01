package designPatterns.abstractfactory

/**
 * The Abstract Factory Design Pattern is a creational design pattern that provides an interface for
 * creating families of related or dependent objects without specifying their concrete classes.
 * This pattern allows you to create a suite of products that follow a common theme or style,
 * ensuring that the products are compatible with each other.
 *
 * Key Components:
 * Abstract Factory: Declares an interface for creating abstract products.
 * Concrete Factory: Implements the operations to create concrete product objects.
 * Abstract Product: Declares an interface for a type of product object.
 * Concrete Product: Defines a product object to be created by the corresponding concrete factory and implements the abstract product interface.
 * Client: Uses only the interfaces declared by the abstract factory and abstract product classes.
 */
object ShoeManufactureDemo {
    fun produceShoe(shoeType: String?): Shoe {
        val shoeFactory = if (shoeType === "Formal") {
            FormalShoeFactory()
        } else if (shoeType === "Sports") {
            SportsShoeFactory()
        } else if (shoeType === "Casual") {
            CasualShoeFactory()
        } else {
            CasualShoeFactory()
        }
        return Shoe(shoeFactory.createShoeSole()!!, shoeFactory.createShoeLace()!!)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val formalShoe = produceShoe("Formal")
        val sportaShoe = produceShoe("Sports")
        val casualShoe = produceShoe("Casual")

        formalShoe.displayBuildShoe()
        sportaShoe.displayBuildShoe()
        casualShoe.displayBuildShoe()
    }
}
