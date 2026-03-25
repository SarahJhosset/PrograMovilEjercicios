package com.ucb.app.booking.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.app.booking.domain.model.BookingModel
import com.ucb.app.booking.domain.usecase.CreateBookingUseCase
import com.ucb.app.booking.presentation.state.BookingEffect
import com.ucb.app.booking.presentation.state.BookingEvent
import com.ucb.app.booking.presentation.state.BookingUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookingViewModel(
    val createBookingUseCase: CreateBookingUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(BookingUiState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<BookingEffect>()
    val effect = _effect.asSharedFlow()

    fun onEvent(event: BookingEvent) {
        when (event) {

            is BookingEvent.OnUserNameChanged -> {
                _state.update {
                    it.copy(
                        userName      = event.value,
                        userNameError = null   // limpia error mientras escribe
                    )
                }
            }

            is BookingEvent.OnSeatsChanged -> {
                _state.update {
                    // Calculamos el precio total automáticamente
                    it.copy(
                        seats = event.value,
                    )
                }
            }

            BookingEvent.OnConfirmBooking -> confirmBooking()

            BookingEvent.OnDismissSuccess -> {
                // El usuario cerró el diálogo de éxito
                _state.update { it.copy(completedBooking = null) }
            }
        }
    }

    // Recibe los datos de la película desde la pantalla de detalle
    fun initWithMovie(title: String, schedule: String) {
        _state.update {
            it.copy(
                movieTitle = title,
                schedule   = schedule
            )
        }
    }

    private fun confirmBooking() {
        val current = _state.value

        // Validación local antes de llamar al UseCase
        if (current.userName.isBlank()) {
            _state.update { it.copy(userNameError = "El nombre es obligatorio") }
            return
        }

        _state.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            try {
                val model = BookingModel(
                    id         = "",
                    movieTitle = current.movieTitle,
                    schedule   = current.schedule,
                    seats      = current.seats,
                    totalPrice = current.seats * current.pricePerSeat,
                    userName   = current.userName
                )

                // El UseCase valida reglas de negocio y llama al Repository
                val result = createBookingUseCase.invoke(model)

                // Guardamos la reserva completada para mostrar el diálogo
                _state.update {
                    it.copy(
                        isLoading        = false,
                        completedBooking = result
                    )
                }

            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false) }
                emit(BookingEffect.ShowError(e.message ?: "Error al reservar"))
            }
        }
    }

    private fun emit(effect: BookingEffect) {
        viewModelScope.launch { _effect.emit(effect) }
    }
}