package lld.genericForGame.turn

import lld.genericForGame.board.Board
import lld.genericForGame.board.BoardPiece

// Abstract Turn Manager class
interface TurnManager<T : BoardPiece> {
    val players: List<String>
    val board : Board<T>
    var currentPlayerIndex: Int

    fun currentPlayer(): String
    fun nextTurn()
    fun performTurn(piece: T, newRow: Int, newCol: Int)
    fun getTurnHistory(): List<String>
}