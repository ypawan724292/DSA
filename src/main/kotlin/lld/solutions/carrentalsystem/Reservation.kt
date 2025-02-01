package lld.solutions.carrentalsystem

import java.time.LocalDate
import java.time.temporal.ChronoUnit

class Reservation(
    val reservationId: String?,
    private val customer: Customer?,
    val car: Car,
    val startDate: LocalDate,
    val endDate: LocalDate?
) {
    val totalPrice: Double

    init {
        this.totalPrice = calculateTotalPrice()
    }

    private fun calculateTotalPrice(): Double {
        val daysRented = ChronoUnit.DAYS.between(startDate, endDate) + 1
        return car.getRentalPricePerDay() * daysRented
    }
}
