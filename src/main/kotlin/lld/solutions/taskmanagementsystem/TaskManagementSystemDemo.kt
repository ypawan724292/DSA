package lld.solutions.taskmanagementsystem

import java.util.*

object TaskManagementSystemDemo {
    fun run() {
        val taskManager: TaskManager = TaskManager.Companion.getInstance()

        // Create users
        val user1 = User("1", "John Doe", "john@example.com")
        val user2 = User("2", "Jane Smith", "jane@example.com")

        // Create tasks
        val task1 = Task("1", "Task 1", "Description 1", Date(), 1, user1)
        val task2 = Task("2", "Task 2", "Description 2", Date(), 2, user2)
        val task3 = Task("3", "Task 3", "Description 3", Date(), 1, user1)

        // Add tasks to the task manager
        taskManager.createTask(task1)
        taskManager.createTask(task2)
        taskManager.createTask(task3)

        // Update a task
        task2.setDescription("Updated description")
        taskManager.updateTask(task2)

        // Search tasks
        val searchResults = taskManager.searchTasks("Task")
        println("Search Results:")
        for (task in searchResults) {
            println(task.getTitle())
        }

        // Filter tasks
        val filteredTasks = taskManager.filterTasks(TaskStatus.PENDING, Date(0), Date(), 1)
        println("Filtered Tasks:")
        for (task in filteredTasks) {
            println(task.getTitle())
        }

        // Mark a task as completed
        taskManager.markTaskAsCompleted("1")

        // Get task history for a user
        val taskHistory = taskManager.getTaskHistory(user1)
        println("Task History for " + user1.getName() + ":")
        for (task in taskHistory) {
            println(task.getTitle())
        }

        // Delete a task
        taskManager.deleteTask("3")
    }
}
