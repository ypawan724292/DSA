package lld.solutions.carrentalsystem

import java.time.LocalDate

object CarRentalSystemDemo {
    fun run() {
        val rentalSystem: RentalSystem = RentalSystem.instance!!

        // Add cars to the rental system
        rentalSystem.addCar(Car("Toyota", "Camry", 2022, "ABC123", 50.0))
        rentalSystem.addCar(Car("Honda", "Civic", 2021, "XYZ789", 45.0))
        rentalSystem.addCar(Car("Ford", "Mustang", 2023, "DEF456", 80.0))

        // Create customers
        val customer1 = Customer("John Doe", "john@example.com", "DL1234")

        // Make reservations
        val startDate = LocalDate.now()
        val endDate = startDate.plusDays(3)
        val availableCars = rentalSystem.searchCars("Toyota", "Camry", startDate, endDate)
        if (!availableCars.isEmpty()) {
            val selectedCar = availableCars.first()
            val reservation = rentalSystem.makeReservation(customer1, selectedCar, startDate, endDate)
            if (reservation != null) {
                val paymentSuccess = rentalSystem.processPayment(reservation)
                if (paymentSuccess) {
                    println("Reservation successful. Reservation ID: " + reservation.reservationId)
                } else {
                    println("Payment failed. Reservation canceled.")
                    rentalSystem.cancelReservation(reservation.reservationId!!)
                }
            } else {
                println("Selected car is not available for the given dates.")
            }
        } else {
            println("No available cars found for the given criteria.")
        }
    }
}
