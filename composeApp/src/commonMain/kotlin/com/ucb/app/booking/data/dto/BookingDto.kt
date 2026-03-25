package com.ucb.app.booking.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookingDto(
    @SerialName("id")
    val id: String,
    @SerialName("movie_title")
    val movieTitle: String,
    @SerialName("schedule")
    val schedule: String,
    @SerialName("seats")
    val seats: Int,
    @SerialName("total_price")
    val totalPrice: Double,
    @SerialName("user_name")
    val userName: String
)