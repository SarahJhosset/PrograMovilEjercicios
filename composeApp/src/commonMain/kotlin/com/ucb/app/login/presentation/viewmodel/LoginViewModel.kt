package com.ucb.app.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.app.login.presentation.state.LoginEffect
import com.ucb.app.login.presentation.state.LoginEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    //mutable  observable (state)

    //effect mutable observable ( shared)
    private val _effect = MutableSharedFlow<LoginEffect>()
    val effect = _effect.asSharedFlow()

    //events
    fun onEvent(event: LoginEvent) {
        when(event) {
            LoginEvent.OnClick -> TODO()
            is LoginEvent.OnEmailChanged -> TODO()
            is LoginEvent.OnPasswordChanged -> TODO()
        }
    }

    //effects
    private fun emit(effect: LoginEffect) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }
}