package lld.genericForGame.turn

import lld.genericForGame.board.Board
import lld.genericForGame.board.BoardPiece
import lld.genericForGame.board.ChessPiece

// Concrete implementation for TurnManager in Chess
class ChessTurnManager(
    override val players: List<String>,
    override val board: Board<ChessPiece>
) : TurnManager<ChessPiece> {

    override var currentPlayerIndex: Int = 0
    private val turnsTaken = mutableListOf<String>()

    override fun currentPlayer(): String = players[currentPlayerIndex]

    override fun nextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size
    }

    override fun performTurn(piece: ChessPiece, newRow: Int, newCol: Int) {
        println("${currentPlayer()} is moving ${piece.describe()} to ($newRow, $newCol)")
        if (piece.validateMove(newRow, newCol, board as Board<BoardPiece>)) {
            piece.move(newRow, newCol)
            turnsTaken.add("${currentPlayer()} moved ${piece.describe()} to ($newRow, $newCol)")
            nextTurn()
        }
    }

    override fun getTurnHistory(): List<String> = turnsTaken
}