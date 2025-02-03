package lld.genericForGame.board

// Concrete implementation for a ChessPiece
data class ChessPiece(
    override val type: String,
    override val color: String
) : BoardPiece {
    override var position: Pair<Int, Int>? = null

    override fun move(newRow: Int, newCol: Int) {
        position = Pair(newRow, newCol)
    }

    override fun describe(): String {
        return "$color $type"
    }

    override fun validateMove(
        newRow: Int,
        newCol: Int,
        board: Board<BoardPiece>
    ): Boolean {
        //validate t
        return true
    }
}