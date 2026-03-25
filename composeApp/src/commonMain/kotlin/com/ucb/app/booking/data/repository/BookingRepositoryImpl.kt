package com.ucb.app.booking.data.repository

import com.ucb.app.booking.data.datasource.BookingRemoteDatasource
import com.ucb.app.booking.data.mapper.toDto
import com.ucb.app.booking.data.mapper.toModel
import com.ucb.app.booking.domain.model.BookingModel
import com.ucb.app.booking.domain.repository.BookingRepository

class BookingRepositoryImpl(
    val remote: BookingRemoteDatasource
) : BookingRepository {

    override suspend fun createBooking(model: BookingModel): BookingModel {
        val dto      = model.toDto()          // Model → DTO para enviar
        val response = remote.createBooking(dto)
        return response.toModel()             // DTO que llega → Model limpio
    }

    override suspend fun getBookings(): List<BookingModel> {
        return remote.getBookings().map { it.toModel() }
    }
}