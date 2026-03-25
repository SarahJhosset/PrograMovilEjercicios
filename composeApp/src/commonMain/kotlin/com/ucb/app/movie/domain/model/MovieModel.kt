package com.ucb.app.movie.domain.model

data class MovieModel(
    val description: String,
    val title: String,
    val pathUrl: String,
    val rating: Int = 0, // Agregamos el rating (0 a 5)
    val schedules: List<String> = emptyList() //lista de horarios de funciones
)