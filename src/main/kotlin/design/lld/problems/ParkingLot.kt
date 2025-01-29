package design.lld.problems

enum class VehicleType { CAR, BIKE, TRUCK }

data class Vehicle(val licensePlate: String, val vehicleType: VehicleType)

data class Ticket(val ticketId: Int, val vehicle: Vehicle)

class ParkingSpot(val spotType: VehicleType) {
    var isAvailable: Boolean = true
    var parkedVehicle: Vehicle? = null

    fun park(vehicle: Vehicle): Boolean {
        return if (isAvailable && vehicle.vehicleType == spotType) {
            parkedVehicle = vehicle
            isAvailable = false
            true
        } else false
    }

    fun free(): Boolean {
        isAvailable = true
        parkedVehicle = null
        return true
    }
}

class Floor(val spots: List<ParkingSpot>) {
    fun findAvailableSpot(vehicle: Vehicle): ParkingSpot? {
        return spots.find { it.isAvailable && it.spotType == vehicle.vehicleType }
    }
}

class ParkingLot(val floors: List<Floor>) {
    private val ticketMap = mutableMapOf<Int, Ticket>()
    private var ticketCounter = 1

    fun parkVehicle(vehicle: Vehicle): Ticket? {
        for (floor in floors) {
            val spot = floor.findAvailableSpot(vehicle)
            if (spot != null) {
                spot.park(vehicle)
                val ticket = Ticket(ticketCounter++, vehicle)
                ticketMap[ticket.ticketId] = ticket
                return ticket
            }
        }
        return null // No spot available
    }

    fun removeVehicle(ticketId: Int): Boolean {
        val ticket = ticketMap.remove(ticketId) ?: return false
        for (floor in floors) {
            for (spot in floor.spots) {
                if (spot.parkedVehicle == ticket.vehicle) {
                    spot.free()
                    return true
                }
            }
        }
        return false
    }
}

fun main() {
    val floor1 = Floor(List(5) { ParkingSpot(VehicleType.CAR) })
    val floor2 = Floor(List(5) { ParkingSpot(VehicleType.BIKE) })
    val parkingLot = ParkingLot(listOf(floor1, floor2))

    val car1 = Vehicle("KA-01-1234", VehicleType.CAR)
    val bike1 = Vehicle("KA-02-5678", VehicleType.BIKE)

    val ticket1 = parkingLot.parkVehicle(car1)
    println("Ticket issued: $ticket1")

    val ticket2 = parkingLot.parkVehicle(bike1)
    println("Ticket issued: $ticket2")

    parkingLot.removeVehicle(ticket1!!.ticketId)
    println("Car removed")
}
