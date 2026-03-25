package com.ucb.app.booking.data.datasource

import com.ucb.app.booking.data.dto.BookingDto

interface BookingRemoteDatasource {
    suspend fun createBooking(dto: BookingDto): BookingDto
    suspend fun getBookings(): List<BookingDto>
}