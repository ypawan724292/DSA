package designPatterns.composite

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