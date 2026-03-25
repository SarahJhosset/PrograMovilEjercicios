package com.ucb.app.login.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ucb.app.login.presentation.composable.EmailField
import com.ucb.app.login.presentation.composable.PasswordField
import com.ucb.app.login.presentation.state.LoginEffect
import com.ucb.app.login.presentation.state.LoginEvent
import com.ucb.app.login.presentation.viewmodel.LoginViewModel
import com.ucb.app.navigation.Routes
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    viewModel: LoginViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsState()

    // Escucha efectos de una sola vez
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                LoginEffect.NavigateToHome -> {
                    // Navega a la cartelera y limpia el historial
                    // para que el usuario no pueda volver al login con "atrás"
                    navController.navigate(Routes.MOVIE_LIST) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
                is LoginEffect.ShowError -> {
                    snackbarHostState.showSnackbar(
                        message = effect.message,
                        withDismissAction = true
                    )
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenido",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Inicia sesión para continuar",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Composables reutilizables — cada uno maneja su propio campo
        EmailField(
            value = state.value.email,
            error = state.value.emailError,
            onValueChange = {
                viewModel.onEvent(LoginEvent.OnEmailChanged(it))
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        PasswordField(
            value   = state.value.password,
            error   = state.value.passwordError,
            visible = state.value.passwordVisible,
            onValueChange = {
                viewModel.onEvent(LoginEvent.OnPasswordChanged(it))
            },
            onVisibilityToggle = {
                viewModel.onEvent(LoginEvent.OnPasswordVisibilityToggled)
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick  = { viewModel.onEvent(LoginEvent.OnLoginClicked) },
            enabled  = !state.value.isLoading,
            modifier = Modifier.fillMaxWidth(),
            shape    = RoundedCornerShape(12.dp)
        ) {
            if (state.value.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text(
                    text = "Iniciar sesión",
                    modifier = Modifier.padding(vertical = 4.dp),
                    fontSize = 16.sp
                )
            }
        }
    }
}