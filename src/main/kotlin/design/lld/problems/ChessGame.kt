package design.lld.problems

/**
 * Chess Game - LLD
 *
 * Chess Game - LLD
 * Functional Requirements
 * Two players play on an 8x8 board.
 * Each player has 16 pieces (King, Queen, Rook, Bishop, Knight, Pawn).
 * Players move pieces according to chess rules.
 * The game ends when a player checkmates the opponent’s King.
 */

enum class PieceType { KING, QUEEN, ROOK, BISHOP, KNIGHT, PAWN }

data class Position(val x: Int, val y: Int)

abstract class Piece(val type: PieceType, val color: String) {
    abstract fun isValidMove(from: Position, to: Position): Boolean
}

class King(color: String) : Piece(PieceType.KING, color) {
    override fun isValidMove(from: Position, to: Position): Boolean {
        val dx = Math.abs(from.x - to.x)
        val dy = Math.abs(from.y - to.y)
        return dx <= 1 && dy <= 1
    }
}

// Similarly, define Queen, Rook, Bishop, Knight, Pawn classes

class ChessBoard {
    private val board: Array<Array<Piece?>> = Array(8) { arrayOfNulls(8) }

    fun placePiece(piece: Piece, position: Position) {
        board[position.x][position.y] = piece
    }

    fun movePiece(from: Position, to: Position): Boolean {
        val piece = board[from.x][from.y] ?: return false
        if (!piece.isValidMove(from, to)) return false

        board[to.x][to.y] = piece
        board[from.x][from.y] = null
        return true
    }

    fun getPieceAt(position: Position): Piece? = board[position.x][position.y]
}

class Player(val name: String, val color: String)

class ChessGame(private val player1: Player, private val player2: Player) {
    private val board = ChessBoard()
    private var currentTurn = player1

    fun movePiece(from: Position, to: Position): Boolean {
        val piece = board.getPieceAt(from) ?: return false
        if (piece.color != currentTurn.color) return false

        if (board.movePiece(from, to)) {
            currentTurn = if (currentTurn == player1) player2 else player1
            return true
        }
        return false
    }
}

fun main() {
    val player1 = Player("Alice", "White")
    val player2 = Player("Bob", "Black")
    val game = ChessGame(player1, player2)

    val moveResult = game.movePiece(Position(0, 1), Position(0, 2))
    println("Move successful: $moveResult")
}


