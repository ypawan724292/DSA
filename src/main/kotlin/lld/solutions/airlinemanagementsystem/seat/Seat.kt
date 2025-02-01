package lld.solutions.airlinemanagementsystem.seat

data class Seat(
    private val seatNumber: String?,
    private val type: SeatType?,
    private var status: SeatStatus? = null
)
