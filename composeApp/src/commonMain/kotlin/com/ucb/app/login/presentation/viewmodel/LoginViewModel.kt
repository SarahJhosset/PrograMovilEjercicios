package com.ucb.app.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.app.login.domain.model.LoginModel
import com.ucb.app.login.domain.usecase.DoLoginUseCase
import com.ucb.app.login.presentation.state.LoginEffect
import com.ucb.app.login.presentation.state.LoginEvent
import com.ucb.app.login.presentation.state.LoginUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    val loginUseCase: DoLoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginUiState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<LoginEffect>()
    val effect = _effect.asSharedFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {

            is LoginEvent.OnEmailChanged -> {
                _state.update {
                    it.copy(
                        email = event.value,
                        // Limpia el error del campo mientras el usuario escribe
                        emailError = null
                    )
                }
            }

            is LoginEvent.OnPasswordChanged -> {
                _state.update {
                    it.copy(
                        password = event.value,
                        passwordError = null
                    )
                }
            }

            LoginEvent.OnPasswordVisibilityToggled -> {
                _state.update {
                    it.copy(passwordVisible = !it.passwordVisible)
                }
            }

            LoginEvent.OnLoginClicked -> submitLogin()
        }
    }

    private fun submitLogin() {
        // Validación local antes de llamar al UseCase
        // Así el usuario ve errores en tiempo real sin esperar la red
        val currentState = _state.value
        var hasError = false

        if (currentState.email.isBlank()) {
            _state.update { it.copy(emailError = "El email es obligatorio") }
            hasError = true
        }
        if (currentState.password.isBlank()) {
            _state.update { it.copy(passwordError = "La contraseña es obligatoria") }
            hasError = true
        }
        // Si hay errores locales, no llames al UseCase todavía
        if (hasError) return

        _state.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            try {
                val model = LoginModel(
                    email    = currentState.email,
                    password = currentState.password
                )
                // El UseCase valida el formato y llama al Repository
                loginUseCase.invoke(model)

                // Login exitoso → emitir efecto de navegación
                emit(LoginEffect.NavigateToHome)

            } catch (e: Exception) {
                // Cualquier error (de validación o de red) llega aquí
                emit(LoginEffect.ShowError(e.message ?: "Error desconocido"))
            } finally {
                // finally siempre se ejecuta, haya error o no
                _state.update { it.copy(isLoading = false) }
            }
        }
    }

    private fun emit(effect: LoginEffect) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }
}