package lld.genericForGame.board

// Abstract Board class
interface Board<T : BoardPiece> {
    val rows: Int
    val cols: Int
    val pieces: MutableList<T>

    fun getPiece(row: Int, col: Int): T?
    fun setPiece(row: Int, col: Int, piece: T)
    fun printBoard()
}