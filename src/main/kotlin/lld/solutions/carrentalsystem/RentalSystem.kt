package lld.solutions.carrentalsystem

import lld.solutions.carrentalsystem.payment.CreditCardPaymentProcessor
import lld.solutions.carrentalsystem.payment.PaymentProcessor
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.ConcurrentHashMap


class RentalSystem private constructor() {

    private val cars: MutableMap<String, Car> = ConcurrentHashMap<String, Car>()
    private val reservations: MutableMap<String, Reservation> = ConcurrentHashMap<String, Reservation>()
    private val paymentProcessor: PaymentProcessor = CreditCardPaymentProcessor()

    fun addCar(car: Car) {
        cars.put(car.licensePlate, car)
    }

    fun removeCar(licensePlate: String?) {
        cars.remove(licensePlate)
    }

    fun searchCars(make: String?, model: String?, startDate: LocalDate, endDate: LocalDate): MutableList<Car> {
        val availableCars: MutableList<Car> = ArrayList<Car>()
        for (car in cars.values) {
            if (car.make.equals(make, ignoreCase = true) && car.model
                    .equals(model, ignoreCase = true) && car.isAvailable
            ) {
                if (isCarAvailable(car, startDate, endDate)) {
                    availableCars.add(car)
                }
            }
        }
        return availableCars
    }

    private fun isCarAvailable(car: Car, startDate: LocalDate, endDate: LocalDate): Boolean {
        for (reservation in reservations.values) {
            if (reservation.car == car) {
                if (startDate.isBefore(reservation.endDate) && endDate.isAfter(reservation.startDate)) {
                    return false
                }
            }
        }
        return true
    }

    @Synchronized
    fun makeReservation(customer: Customer?, car: Car, startDate: LocalDate, endDate: LocalDate): Reservation? {
        if (isCarAvailable(car, startDate, endDate)) {
            val reservationId = generateReservationId()
            val reservation = Reservation(reservationId, customer, car, startDate, endDate)
            reservations.put(reservationId, reservation)
            car.isAvailable = false
            return reservation
        }
        return null
    }

    @Synchronized
    fun cancelReservation(reservationId: String) {
        val reservation = reservations.remove(reservationId)
        reservation?.car?.isAvailable = true
    }

    fun processPayment(reservation: Reservation): Boolean {
        return paymentProcessor.processPayment(reservation.totalPrice)
    }

    private fun generateReservationId(): String {
        val timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
        return "RES" + timeStamp.toString().substring(0, 8).uppercase()
    }

    companion object {
        @get:Synchronized
        var instance: RentalSystem? = null
            get() {
                if (field == null) {
                    field = RentalSystem()
                }
                return field
            }
            private set
    }
}
