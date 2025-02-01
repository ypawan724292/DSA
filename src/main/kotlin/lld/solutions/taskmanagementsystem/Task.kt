package lld.solutions.taskmanagementsystem

import java.util.*

class Task(// Getters and setters
    val id: String?,
    var title: String?,
    var description: String?,
    var dueDate: Date?,
    var priority: Int,
    val assignedUser: User?
) {
    var status: TaskStatus?

    init {
        this.status = TaskStatus.PENDING
    }
}
