package lld.genericForGame

import lld.genericForGame.board.ChessBoard
import lld.genericForGame.board.ChessPiece
import lld.genericForGame.game.ChessGame
import lld.genericForGame.turn.ChessTurnManager

fun main() {
    // Create the players
    val players = listOf("Player 1", "Player 2")

    // Create the game components
    val board = ChessBoard()
    val turnManager = ChessTurnManager(players, board)

    // Create the game
    val game = ChessGame(board, turnManager)

    // Start the game
    game.startGame()

    // Play some turns
    val knight = ChessPiece("Knight", "White")
    game.playTurn(knight, 2, 2) // Player 1 moves knight

    val pawn = ChessPiece("Pawn", "Black")
    game.playTurn(pawn, 2, 1) // Player 2 moves pawn
}
