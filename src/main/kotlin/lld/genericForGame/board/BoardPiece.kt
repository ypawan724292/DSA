package lld.genericForGame.board

// Abstract Board Piece class
interface BoardPiece {
    val type: String
    val color: String
    var position: Pair<Int, Int>?

    fun move(newRow: Int, newCol: Int)
    fun describe(): String
    fun validateMove(newRow: Int, newCol: Int, board: Board<BoardPiece>): Boolean
}



