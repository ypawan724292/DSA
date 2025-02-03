package lld.genericForGame.board

import lld.genericForGame.board.ChessPiece

// Concrete implementation for a ChessBoard
class ChessBoard(override val rows: Int = 8, override val cols: Int = 8) : Board<ChessPiece> {
    override val pieces: MutableList<ChessPiece> = mutableListOf()

    override fun getPiece(row: Int, col: Int): ChessPiece? {
        return pieces.find { it.position == Pair(row, col) }
    }

    override fun setPiece(row: Int, col: Int, piece: ChessPiece) {
        piece.position = Pair(row, col)
        pieces.add(piece)
    }

    override fun printBoard() {
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                val piece = getPiece(i, j)
                print("${piece?.describe() ?: "Empty"}\t")
            }
            println()
        }
    }
}