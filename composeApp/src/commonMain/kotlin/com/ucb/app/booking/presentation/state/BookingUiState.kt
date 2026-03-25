package com.ucb.app.booking.presentation.state

import com.ucb.app.booking.domain.model.BookingModel

data class BookingUiState(
    val movieTitle  : String       = "",
    val schedule    : String       = "",
    val userName    : String       = "",
    val seats       : Int          = 1,
    val pricePerSeat: Double       = 12.50,
    val isLoading   : Boolean      = false,
    val userNameError: String?     = null,
    // null = reserva no completada, BookingModel = reserva exitosa
    val completedBooking: BookingModel? = null
)