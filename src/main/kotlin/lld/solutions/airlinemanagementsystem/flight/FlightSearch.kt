package lld.solutions.airlinemanagementsystem.flight

import java.time.LocalDate
import java.util.stream.Collectors

class FlightSearch(private val flights: MutableList<Flight>) {

    fun searchFlights(source: String, destination: String, date: LocalDate): List<Flight> {
        val list = flights
            .filter { flight ->
                flight.source.contains(source, ignoreCase = true)
                        && flight.destination.contains(destination, ignoreCase = true)
                        && flight.departureTime.toLocalDate() == date
            }.map {
                it
            }
        return list
    }
}
