package lld.solutions.taskmanagementsystem

import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList

class TaskManager private constructor() {
    private val tasks: MutableMap<String?, Task>
    private val userTasks: MutableMap<String?, MutableList<Task?>?>

    init {
        tasks = ConcurrentHashMap<String?, Task>()
        userTasks = ConcurrentHashMap<String?, MutableList<Task?>?>()
    }

    fun createTask(task: Task) {
        tasks.put(task.getId(), task)
        assignTaskToUser(task.getAssignedUser(), task)
    }

    fun updateTask(updatedTask: Task) {
        val existingTask = tasks.get(updatedTask.getId())
        if (existingTask != null) {
            synchronized(existingTask) {
                existingTask.setTitle(updatedTask.getTitle())
                existingTask.setDescription(updatedTask.getDescription())
                existingTask.setDueDate(updatedTask.getDueDate())
                existingTask.setPriority(updatedTask.getPriority())
                existingTask.setStatus(updatedTask.getStatus())
                val previousUser = existingTask.getAssignedUser()
                val newUser = updatedTask.getAssignedUser()
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
            unassignTaskFromUser(task.getAssignedUser(), task)
        }
    }

    fun searchTasks(keyword: String): MutableList<Task?> {
        val matchingTasks: MutableList<Task?> = ArrayList<Task?>()
        for (task in tasks.values) {
            if (task.getTitle().contains(keyword) || task.getDescription().contains(keyword)) {
                matchingTasks.add(task)
            }
        }
        return matchingTasks
    }

    fun filterTasks(status: TaskStatus?, startDate: Date?, endDate: Date?, priority: Int): MutableList<Task?> {
        val filteredTasks: MutableList<Task?> = ArrayList<Task?>()
        for (task in tasks.values) {
            if (task.getStatus() == status && task.getDueDate().compareTo(startDate) >= 0 && task.getDueDate()
                    .compareTo(endDate) <= 0 && task.getPriority() == priority
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
                task.setStatus(TaskStatus.COMPLETED)
            }
        }
    }

    fun getTaskHistory(user: User): MutableList<Task?> {
        return ArrayList<Task?>(userTasks.getOrDefault(user.getId(), ArrayList<Task?>()))
    }

    private fun assignTaskToUser(user: User, task: Task?) {
        userTasks.computeIfAbsent(user.getId()) { k: kotlin.String? -> CopyOnWriteArrayList<lld.solutions.taskmanagementsystem.Task?>() }!!
            .add(task)
    }

    private fun unassignTaskFromUser(user: User, task: Task?) {
        val tasks = userTasks.get(user.getId())
        if (tasks != null) {
            tasks.remove(task)
        }
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
