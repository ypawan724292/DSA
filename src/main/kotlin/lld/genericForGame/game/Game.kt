package lld.genericForGame.game

import lld.genericForGame.board.Board
import lld.genericForGame.board.BoardPiece
import lld.genericForGame.turn.TurnManager

// Abstract Game class
abstract class Game<T : BoardPiece, B : Board<T>, TM : TurnManager<T>>(
    val board: B,
    val turnManager: TM
) {
    abstract fun startGame()
    abstract fun playTurn(piece: T, newRow: Int, newCol: Int)
}
