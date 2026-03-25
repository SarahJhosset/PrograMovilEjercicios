package com.ucb.app.booking.domain.model

data class BookingModel(
    val id: String,
    val movieTitle: String,
    val schedule: String,
    val seats: Int,
    val totalPrice: Double,
    val userName: String
)