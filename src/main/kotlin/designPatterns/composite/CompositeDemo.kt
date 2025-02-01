package designPatterns.composite

/**
 * The Composite Design Pattern is a structural design pattern that allows you to compose objects into tree structures to
 * represent part-whole hierarchies. It lets clients treat individual objects and compositions of objects uniformly.
 * Key Components:
 * Component: Declares the interface for objects in the composition and implements default behavior for the interface common to all classes.
 * Leaf: Represents leaf objects in the composition. A leaf has no children.
 * Composite: Defines behavior for components having children and stores child components.
 * Client: Manipulates objects in the composition through the Component interface.
 */
object CompositeDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        val dev1 = Developer("John Doe", 100000.0)
        val dev2 = Developer("Jane Smith", 120000.0)
        val designer = Designer("Mike Johnson", 90000.0)

        val engManager = Manager("Emily Brown", 200000.0)
        engManager.addEmployee(dev1)
        engManager.addEmployee(dev2)
        engManager.addEmployee(designer)

        val generalManager = Manager("David Wilson", 300000.0)
        generalManager.addEmployee(engManager)

        println("Company Structure:")
        generalManager.showDetails()

        println("\nTotal Salary Budget: $" + generalManager.getSalary())
    }
}