package lld.solutions.airlinemanagementsystem.flight

import lld.solutions.airlinemanagementsystem.seat.Seat
import java.time.LocalDateTime

data class Flight(
    val flightNumber: String,
    val source: String,
    val destination: String,
    val departureTime: LocalDateTime,
    val arrivalTime: LocalDateTime,
    val availableSeats: List<Seat>
)
