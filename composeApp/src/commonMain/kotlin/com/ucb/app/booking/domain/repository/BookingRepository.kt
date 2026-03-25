package com.ucb.app.booking.domain.repository

import com.ucb.app.booking.domain.model.BookingModel

interface BookingRepository {
    suspend fun createBooking(model: BookingModel): BookingModel
    suspend fun getBookings(): List<BookingModel>
}