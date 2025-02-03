package lld.genericForGame.game

import lld.genericForGame.board.ChessBoard
import lld.genericForGame.board.ChessPiece
import lld.genericForGame.turn.ChessTurnManager

// Concrete implementation for a ChessGame
class ChessGame(
    board: ChessBoard,
    turnManager: ChessTurnManager
) : Game<ChessPiece, ChessBoard, ChessTurnManager>(board, turnManager) {

    override fun startGame() {
        println("Starting the game!")
        // Add pieces to the board (e.g., placing pawns, kings, etc.)
        board.setPiece(0, 0, ChessPiece("Rook", "White"))
        board.setPiece(1, 0, ChessPiece("Pawn", "White"))
        board.setPiece(0, 1, ChessPiece("Knight", "White"))
        board.setPiece(0, 2, ChessPiece("Bishop", "White"))
        // Print initial board setup
        board.printBoard()
    }

    override fun playTurn(piece: ChessPiece, newRow: Int, newCol: Int) {
        turnManager.performTurn(piece, newRow, newCol)
        board.printBoard()  // Show board after each turn
    }
}