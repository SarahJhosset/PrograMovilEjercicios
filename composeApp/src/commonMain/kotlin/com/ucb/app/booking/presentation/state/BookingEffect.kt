package com.ucb.app.booking.presentation.state

sealed interface BookingEffect {
    data class ShowError(val message: String) : BookingEffect
}