package designPatterns.composite

internal class Developer(private val name: String?, private val salary: Double) : Employee {
    override fun showDetails() {
        println("Developer: " + name + ", Salary: $" + salary)
    }

    override fun getSalary(): Double {
        return salary
    }
}