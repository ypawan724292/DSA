package lld.solutions.carrentalsystem

class Car(
    val make: String,
    val model: String,
    private val year: Int,
    val licensePlate: String,
    val rentalPricePerDay: Double
) {
    var isAvailable: Boolean = true
}
