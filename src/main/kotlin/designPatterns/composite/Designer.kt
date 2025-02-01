package designPatterns.composite

internal class Designer(private val name: String?, private val salary: Double) : Employee {
    override fun showDetails() {
        println("Designer: " + name + ", Salary: $" + salary)
    }

    override fun getSalary(): Double {
        return salary
    }
}
