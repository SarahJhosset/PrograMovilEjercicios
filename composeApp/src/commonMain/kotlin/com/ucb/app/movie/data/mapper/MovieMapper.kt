package com.ucb.app.movie.data.mapper

import com.ucb.app.movie.data.dto.MovieDto
import com.ucb.app.movie.domain.model.MovieModel

fun MovieDto.toModel() = MovieModel(
    description = "",
    pathUrl = "https://image.tmdb.org/t/p/w500$posterPath",
    title = title,
    schedules   = listOf("14:00", "16:30", "19:00", "21:30")
)
