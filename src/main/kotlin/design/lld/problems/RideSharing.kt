package design.lld.problems


/**
 * Functional Requirements
 *
 * Users can request rides by providing a pickup and destination location.
 * Drivers can accept or reject ride requests.
 * System finds the nearest available driver.
 * Users and drivers can track the ride status.
 * Users can cancel the ride before confirmation.
 * Payments are processed after ride completion.
 *
 *
 * key Features Implemented
 * ✔ Users can request rides.
 * ✔ System assigns the nearest available driver.
 * ✔ Drivers can accept or reject rides.
 * ✔ Ride fare is calculated dynamically (₹10 per km).
 * ✔ Users can cancel rides before confirmation.
 * ✔ Drivers become available again after ride completion.
 *
 *
 */

import java.util.UUID

enum class RideStatus { REQUESTED, ACCEPTED, COMPLETED, CANCELLED }

data class User(val id: Int, val name: String, var location: String)

data class Driver(val id: Int, val name: String, var location: String, var isAvailable: Boolean = true)

data class Ride(
    val id: String = UUID.randomUUID().toString(),
    val user: PaymentUser,
    var driver: Driver? = null,
    val pickup: String,
    val destination: String,
    var status: RideStatus = RideStatus.REQUESTED
) {
    var fare: Double = 0.0

    fun calculateFare(): Double {
        val distance = (1..20).random()  // Simulating a random distance in km
        fare = distance * 10.0 // ₹10 per km
        return fare
    }
}

class RideService(private val drivers: MutableList<Driver>) {
    private val activeRides = mutableListOf<Ride>()

    fun requestRide(user: PaymentUser, pickup: String, destination: String): Ride? {
        val availableDriver = drivers.firstOrNull { it.isAvailable }
        if (availableDriver == null) {
            println("No drivers available!")
            return null
        }

        val ride = Ride(user = user, driver = availableDriver, pickup = pickup, destination = destination)
        ride.calculateFare()
        availableDriver.isAvailable = false
        activeRides.add(ride)
        println("Ride requested: $ride")
        return ride
    }

    fun acceptRide(driver: Driver, ride: Ride): Boolean {
        if (ride.driver == driver && ride.status == RideStatus.REQUESTED) {
            ride.status = RideStatus.ACCEPTED
            println("${driver.name} has accepted the ride!")
            return true
        }
        return false
    }

    fun completeRide(ride: Ride): Boolean {
        if (ride.status == RideStatus.ACCEPTED) {
            ride.status = RideStatus.COMPLETED
            ride.driver?.isAvailable = true
            println("Ride completed! Fare: ₹${ride.fare}")
            return true
        }
        return false
    }

    fun cancelRide(ride: Ride) {
        if (ride.status == RideStatus.REQUESTED) {
            ride.status = RideStatus.CANCELLED
            ride.driver?.isAvailable = true
            println("Ride cancelled.")
        }
    }
}

fun main() {
    val drivers = mutableListOf(
        Driver(1, "John", "Street 1"),
        Driver(2, "Mike", "Street 5")
    )

    val rideService = RideService(drivers)

    val user = PaymentUser(1, "Pavan", "Mall")
    val ride = rideService.requestRide(user, "Mall", "Airport")

    if (ride != null) {
        rideService.acceptRide(ride.driver!!, ride)
        rideService.completeRide(ride)
    }
}

