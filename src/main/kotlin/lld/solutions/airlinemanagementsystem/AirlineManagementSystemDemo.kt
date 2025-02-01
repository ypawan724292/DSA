package lld.solutions.airlinemanagementsystem

import lld.solutions.airlinemanagementsystem.flight.Flight
import lld.solutions.airlinemanagementsystem.seat.Seat
import lld.solutions.airlinemanagementsystem.seat.SeatType
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * Flow:
 *
 * User ->
 * System ->
 * GetAll Flights for given route and time ->
 * Select one of the flights ->
 * Book a seat ->
 * Give passenger details ->
 * Confirm booking {Booking ID} ->
 *
 *
 *
 * User-> System ->
 * Booking Manager->
 * Get By Booking Id->
 * Update in for like passenger details, seat details, etc.->
 * Confirm Update {Booking ID} ->
 *
 *
 *
 * Entity:
 *
 * Flight
 *  1. Flight Number
 *  2. Source
 *  3. Destination
 *  4. Departure Time
 *  5. Arrival Time
 *  6. List of Seats
 *
 *
 * User,
 * 1. name
 * 2. email
 * 3. phone
 * 4. UserType
 * 5. IdProof
 *
 * UserType
 * Admin
 * Passenger
 * Crew
 *
 * Seats,
 * 1. row
 * 2. col
 * 3. name
 * 4. type {MIDDLE, WINDOW, AISLE}
 * 5. class {ECONOMY, BUSINESS, FIRST}
 * 6. Lauguage cap
 * 7. price
 *
 *
 * Price
 * 1. Base fare
 * 2. Tax
 * 3. Discount
 * 4. Total
 *
 * Booking
 * 1. Booking ID
 * 2. Passenger
 * 3. Flight
 * 4. Seat
 * 5. Price
 * 6. Status {Confirmed, Cancelled}
 *
 *
 *
 *
 */

object AirlineManagementSystemDemo {
    fun run() {
        val airlineManagementSystem = AirlineManagementSystem()

        // Create users
        val passenger1 = Passenger("U001", "John Doe", "john@example.com", "1234567890")

        // Create flights
        val departureTime1 = LocalDateTime.now().plusDays(1)
        val arrivalTime1 = departureTime1.plusHours(2)
        val flight1 = Flight("F001", "New York", "London", departureTime1, arrivalTime1, listOf<Seat>())

        val departureTime2 = LocalDateTime.now().plusDays(3)
        val arrivalTime2 = departureTime2.plusHours(5)
        val flight2 = Flight("F002", "Paris", "Tokyo", departureTime2, arrivalTime2, listOf<Seat>())

        airlineManagementSystem.addFlight(flight1)
        airlineManagementSystem.addFlight(flight2)

        // Create aircrafts
        val aircraft1 = Aircraft("A001", "Boeing 747", 300)
        val aircraft2 = Aircraft("A002", "Airbus A380", 500)
        airlineManagementSystem.addAircraft(aircraft1)
        airlineManagementSystem.addAircraft(aircraft2)

        // Search flights
        val searchResults = airlineManagementSystem.searchFlights("New York", "London", LocalDate.now().plusDays(1))
        println("Search Results:")
        for (flight in searchResults) {
            println("Flight: " + flight.flightNumber + " - " + flight.source + " to " + flight.destination)
        }

        val seat = searchResults.first().availableSeats.random()

        // Book a flight
        val booking = airlineManagementSystem.bookFlight(flight1, passenger1, seat, 100.0)
        if (booking != null) {
            println("Booking successful. Booking ID: " + booking.bookingNumber)
        } else {
            println("Booking failed.")
        }

        // Cancel a booking
        airlineManagementSystem.cancelBooking(booking!!.bookingNumber)
        println("Booking cancelled.")
    }
}
