package com.ucb.app.booking.data.datasource

import com.ucb.app.booking.data.dto.BookingDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json

class BookingService : BookingRemoteDatasource {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint        = true
                isLenient          = true
                ignoreUnknownKeys  = true
            })
        }
    }

    override suspend fun createBooking(dto: BookingDto): BookingDto {
        // Simulamos demora de red — reemplaza con tu endpoint real
        delay(1500)
        // Devolvemos el mismo dto con un id generado
        return dto.copy(id = "BKG-${System.currentTimeMillis()}")
    }

    override suspend fun getBookings(): List<BookingDto> {
        delay(800)
        return emptyList()
    }
}