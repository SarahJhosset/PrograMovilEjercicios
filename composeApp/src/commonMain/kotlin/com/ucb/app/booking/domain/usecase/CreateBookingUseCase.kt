package com.ucb.app.booking.domain.usecase

import com.ucb.app.booking.domain.model.BookingModel
import com.ucb.app.booking.domain.repository.BookingRepository

class CreateBookingUseCase(
    val repository: BookingRepository
) {
    suspend fun invoke(model: BookingModel): BookingModel {
        if (model.userName.isBlank()) {
            throw IllegalArgumentException("El nombre no puede estar vacío")
        }
        if (model.seats < 1) {
            throw IllegalArgumentException("Debes seleccionar al menos 1 asiento")
        }
        if (model.seats > 10) {
            throw IllegalArgumentException("No puedes reservar más de 10 asientos")
        }
        return repository.createBooking(model)
    }
}