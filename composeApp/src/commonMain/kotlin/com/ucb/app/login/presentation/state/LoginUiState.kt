package com.ucb.app.login.presentation.state

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val passwordVisible: Boolean = false,  // para el ojo que muestra/oculta
    // Mensajes de error por campo — null significa sin error
    val emailError: String? = null,
    val passwordError: String? = null
)