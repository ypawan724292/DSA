import kotlin.random.Random
import kotlin.concurrent.thread
import java.util.LinkedList

enum class Direction { UP, DOWN, LEFT, RIGHT }

data class Position(val x: Int, val y: Int)

class Board(val width: Int, val height: Int) {
    fun isWithinBounds(pos: Position): Boolean {
        return pos.x in 0 until width && pos.y in 0 until height
    }
}

class Snake(startPos: Position) {
    val body: LinkedList<Position> = LinkedList()
    var direction: Direction = Direction.RIGHT

    init {
        body.add(startPos)
    }

    fun move() {
        val head = body.first
        val newHead = getNextPosition(head)
        body.addFirst(newHead)
        body.removeLast() // Remove tail to maintain size
    }

    fun grow() {
        val head = body.first
        body.addFirst(getNextPosition(head)) // Extend the head
    }

    private fun getNextPosition(head: Position): Position {
        return when (direction) {
            Direction.UP -> Position(head.x, head.y - 1)
            Direction.DOWN -> Position(head.x, head.y + 1)
            Direction.LEFT -> Position(head.x - 1, head.y)
            Direction.RIGHT -> Position(head.x + 1, head.y)
        }
    }

    fun checkCollision(pos: Position): Boolean {
        return body.contains(pos)
    }
}

class Food(board: Board) {
    var position: Position = generateNewPosition(board)

    fun generateNewPosition(board: Board): Position {
        return Position(Random.nextInt(board.width), Random.nextInt(board.height))
    }
}

class Game(val width: Int, val height: Int) {
    private val board = Board(width, height)
    private val snake = Snake(Position(width / 2, height / 2))
    private var food = Food(board)
    private var isRunning = true

    fun start() {
        thread {
            while (isRunning) {
                update()
                Thread.sleep(500) // Slow the game loop
            }
        }
    }

    private fun update() {
        snake.move()
        checkCollision()
        if (snake.body.first == food.position) {
            snake.grow()
            food = Food(board)
        }
    }

    fun handleInput(direction: Direction) {
        snake.direction = direction
    }

    private fun checkCollision() {
        val head = snake.body.first
        if (!board.isWithinBounds(head) || snake.checkCollision(head)) {
            isRunning = false
            println("Game Over!")
        }
    }
}

fun main() {
    val game = Game(10, 10)
    game.start()
}


/**
 *
 *
 * Role : SDE 2
 * DSA
 * LLD : + Android
 * Android : domain specific + DSA
 * Culture Behaviour : We can expect anything
 *
 *
 */