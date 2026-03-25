package com.ucb.app.booking.presentation.state

sealed interface BookingEvent {
    data class OnUserNameChanged(val value: String) : BookingEvent
    data class OnSeatsChanged(val value: Int)       : BookingEvent
    object OnConfirmBooking                         : BookingEvent
    object OnDismissSuccess                         : BookingEvent
}