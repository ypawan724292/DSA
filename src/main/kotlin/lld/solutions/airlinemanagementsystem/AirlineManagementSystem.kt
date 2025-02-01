package lld.solutions.airlinemanagementsystem

import lld.solutions.airlinemanagementsystem.booking.Booking
import lld.solutions.airlinemanagementsystem.booking.BookingManager
import lld.solutions.airlinemanagementsystem.flight.Flight
import lld.solutions.airlinemanagementsystem.flight.FlightSearch
import lld.solutions.airlinemanagementsystem.payment.Payment
import lld.solutions.airlinemanagementsystem.payment.PaymentProcessor
import lld.solutions.airlinemanagementsystem.seat.Seat
import java.time.LocalDate


class AirlineManagementSystem {

    private val flights = mutableListOf<Flight>()
    private val aircrafts = mutableListOf<Aircraft>()
    private val flightSearch = FlightSearch(flights)
    private val bookingManager = BookingManager.instance
    private val paymentProcessor = PaymentProcessor.instance


    fun addFlight(flight: Flight) {
        flights.add(flight)
    }

    fun addAircraft(aircraft: Aircraft) {
        aircrafts.add(aircraft)
    }

    fun searchFlights(source: String, destination: String, date: LocalDate): List<Flight> {
        return flightSearch.searchFlights(source, destination, date)
    }

    fun bookFlight(flight: Flight, passenger: Passenger, seat: Seat, price: Double): Booking? {
        return bookingManager?.createBooking(flight, passenger, seat, price)
    }

    fun cancelBooking(bookingNumber: String) {
        bookingManager?.cancelBooking(bookingNumber)
    }

    fun processPayment(payment: Payment) {
        paymentProcessor?.processPayment(payment)
    }
}
