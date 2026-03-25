package com.ucb.app.booking.data.mapper

import com.ucb.app.booking.data.dto.BookingDto
import com.ucb.app.booking.domain.model.BookingModel

// DTO que llega del servidor → Model limpio que usa el Domain
fun BookingDto.toModel() = BookingModel(
    id         = id,
    movieTitle = movieTitle,
    schedule   = schedule,
    seats      = seats,
    totalPrice = totalPrice,
    userName   = userName
)

// Model del Domain → DTO para enviar al servidor
fun BookingModel.toDto() = BookingDto(
    id         = id,
    movieTitle = movieTitle,
    schedule   = schedule,
    seats      = seats,
    totalPrice = totalPrice,
    userName   = userName
)