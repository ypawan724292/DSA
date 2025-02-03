package lld.solutions.taskmanagementsystem

import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList

class TaskManager private constructor() {
    private val tasks: MutableMap<String, Task> = ConcurrentHashMap<String, Task>()
    private val userTasks: MutableMap<String, MutableList<Task>> = ConcurrentHashMap<String, MutableList<Task>>()

    fun createTask(task: Task) {
        tasks.put(task.id, task)
        assignTaskToUser(task.assignedUser, task)
    }

    fun updateTask(updatedTask: Task) {
        val existingTask = tasks.get(updatedTask.id)
        if (existingTask != null) {
            synchronized(existingTask) {
                existingTask.title = updatedTask.title
                existingTask.description = updatedTask.description
                existingTask.dueDate = updatedTask.dueDate
                existingTask.priority = updatedTask.priority
                existingTask.status = updatedTask.status
                val previousUser = existingTask.assignedUser
                val newUser = updatedTask.assignedUser
                if (previousUser != newUser) {
                    unassignTaskFromUser(previousUser, existingTask)
                    assignTaskToUser(newUser, existingTask)
                }
            }
        }
    }

    fun deleteTask(taskId: String?) {
        val task = tasks.remove(taskId)
        if (task != null) {
            unassignTaskFromUser(task.assignedUser, task)
        }
    }

    fun searchTasks(keyword: String): MutableList<Task?> {
        val matchingTasks: MutableList<Task?> = ArrayList<Task?>()
        for (task in tasks.values) {
            if (task.title.contains(keyword) || task.description.contains(keyword)) {
                matchingTasks.add(task)
            }
        }
        return matchingTasks
    }

    fun filterTasks(status: TaskStatus?, startDate: Date?, endDate: Date?, priority: Int): MutableList<Task?> {
        val filteredTasks: MutableList<Task?> = ArrayList<Task?>()
        for (task in tasks.values) {
            if (task.status == status && task.dueDate.compareTo(startDate) >= 0 && task.dueDate
                    .compareTo(endDate) <= 0 && task.priority == priority
            ) {
                filteredTasks.add(task)
            }
        }
        return filteredTasks
    }

    fun markTaskAsCompleted(taskId: String?) {
        val task = tasks.get(taskId)
        if (task != null) {
            synchronized(task) {
                task.status = TaskStatus.COMPLETED
            }
        }
    }

    fun getTaskHistory(user: User): MutableList<Task?> {
        return ArrayList<Task?>(userTasks.getOrDefault(user.id, ArrayList<Task?>()))
    }

    private fun assignTaskToUser(user: User, task: Task) {
        userTasks.putIfAbsent(user.id.toString(), CopyOnWriteArrayList<Task>())
    }

    private fun unassignTaskFromUser(user: User, task: Task?) {
        val tasks = userTasks.get(user.id)
        tasks?.remove(task)
    }

    companion object {
        @get:Synchronized
        var instance: TaskManager? = null
            get() {
                if (field == null) {
                    field = TaskManager()
                }
                return field
            }
            private set
    }
}
