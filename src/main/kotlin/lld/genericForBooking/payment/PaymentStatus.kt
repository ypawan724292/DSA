package lld.genericForBooking.payment

import lld.genericForBooking.booking.User
import java.util.UUID

enum class PaymentStatus { SUCCESS, FAILURE }

data class Payment(
    val paymentId: String = UUID.randomUUID().toString(),
    val user: User,
    val amount: Double,
    val paymentMethod: String,
    val status: PaymentStatus
)