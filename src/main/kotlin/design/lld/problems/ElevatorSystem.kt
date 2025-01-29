package design.lld.problems

import java.util.PriorityQueue

/**
 * Elevator System - LLD
 *
 * Functional Requirements
 * Multiple elevators in a building.
 * Users can request an elevator from any floor.
 * Elevators process requests optimally (nearest elevator picks the request).
 * Elevators can move up, down, or stay idle.
 * Elevators have capacity limits and handle weight restrictions.
 *
 *
 * Non-Functional Requirements
 * Scalable and maintainable design.
 * Uses OOP principles (Encapsulation, Inheritance, etc.).
 * Optimized request handling for minimum wait time.
 *
 *
 *  Key Design Decisions
 * ✔ Encapsulation: Elevator’s requestQueue is private and controlled via methods.
 * ✔ Scalability: Supports multiple elevators, easily extendable for more logic (like weight limits).
 * ✔ Efficiency: Uses PriorityQueue for optimized request handling.
 * ✔ Optimized Request Allocation: Nearest elevator picks up the request.
 */


enum class Direction { UP, DOWN, IDLE }
enum class Status { MOVING, STOPPED, IDLE }

class Elevator(val id: Int) {
    var currentFloor: Int = 0
    var direction: Direction = Direction.IDLE
    var status: Status = Status.IDLE
    private val requestQueue = PriorityQueue<Int>()

    fun addRequest(floor: Int) {
        requestQueue.add(floor)
        if (status == Status.IDLE) {
            move()
        }
    }

    fun move() {
        while (requestQueue.isNotEmpty()) {
            val nextFloor = requestQueue.poll()
            direction = if (nextFloor > currentFloor) Direction.UP else Direction.DOWN
            println("Elevator $id moving from Floor $currentFloor to Floor $nextFloor")
            currentFloor = nextFloor
            status = Status.STOPPED
            println("Elevator $id stopped at Floor $currentFloor")
        }
        direction = Direction.IDLE
        status = Status.IDLE
    }

    fun isIdle(): Boolean = status == Status.IDLE
}

class ElevatorController(private val elevators: List<Elevator>) {
    fun requestElevator(floor: Int): Elevator {
        val availableElevator = elevators.minByOrNull { Math.abs(it.currentFloor - floor) } ?: elevators.first()
        println("Elevator ${availableElevator.id} assigned to Floor $floor")
        availableElevator.addRequest(floor)
        return availableElevator
    }
}

fun main() {
    val elevators = listOf(Elevator(1), Elevator(2), Elevator(3))
    val controller = ElevatorController(elevators)

    controller.requestElevator(5)
    controller.requestElevator(2)
    controller.requestElevator(8)
}


