package lld.solutions.splitwise

class Transaction(
    private val id: String?,
    private val sender: User?,
    private val receiver: User?,
    private val amount: Double
)
