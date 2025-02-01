package designPatterns.composite

internal class Manager(private val name: String?, private val salary: Double) : Employee {
    private val subordinates: List<Employee?> = ArrayList()

    fun addEmployee(employee: Employee?) {
        subordinates.add(employee)
    }

    fun removeEmployee(employee: Employee?) {
        subordinates.remove(employee)
    }

    override fun showDetails() {
        println("Manager: " + name + ", Salary: $" + salary)
        println("Subordinates:")
        for (employee in subordinates) {
            employee!!.showDetails()
        }
    }

    override fun getSalary(): Double {
        var totalSalary = salary
        for (employee in subordinates) {
            totalSalary += employee!!.getSalary()
        }
        return totalSalary
    }
}