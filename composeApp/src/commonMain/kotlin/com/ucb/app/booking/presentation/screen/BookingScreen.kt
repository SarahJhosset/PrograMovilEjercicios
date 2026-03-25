package com.ucb.app.booking.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ucb.app.booking.presentation.state.BookingEffect
import com.ucb.app.booking.presentation.state.BookingEvent
import com.ucb.app.booking.presentation.viewmodel.BookingViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(
    movieTitle    : String,
    schedule      : String,
    navController : NavController,
    viewModel     : BookingViewModel = koinViewModel()
) {
    val state             = viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    // Inicializa el ViewModel con los datos de la película
    LaunchedEffect(movieTitle, schedule) {
        viewModel.initWithMovie(movieTitle, schedule)
    }

    // Escucha efectos de una sola vez
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is BookingEffect.ShowError ->
                    snackbarHostState.showSnackbar(effect.message)
            }
        }
    }

    // Diálogo de confirmación cuando la reserva fue exitosa
    if (state.value.completedBooking != null) {
        val booking = state.value.completedBooking!!
        AlertDialog(
            onDismissRequest = {
                viewModel.onEvent(BookingEvent.OnDismissSuccess)
                navController.popBackStack()
            },
            title   = { Text("¡Reserva confirmada!") },
            text    = {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text("Código: ${booking.id}")
                    Text("Película: ${booking.movieTitle}")
                    Text("Horario: ${booking.schedule}")
                    Text("Asientos: ${booking.seats}")
                    Text(
                        text       = "Total: $${booking.totalPrice}",
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.onEvent(BookingEvent.OnDismissSuccess)
                    navController.popBackStack()
                }) {
                    Text("Aceptar")
                }
            }
        )
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Reservar entrada") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        },
        bottomBar = {
            Button(
                onClick  = { viewModel.onEvent(BookingEvent.OnConfirmBooking) },
                enabled  = !state.value.isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                if (state.value.isLoading) {
                    CircularProgressIndicator(
                        modifier    = Modifier.size(20.dp),
                        strokeWidth = 2.dp,
                        color       = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    Text(
                        text     = "Confirmar reserva — $${"%.2f".format(
                            state.value.seats * state.value.pricePerSeat
                        )}",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Resumen de la película
            Column {
                Text(
                    text       = state.value.movieTitle,
                    fontSize   = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text  = "Horario: ${state.value.schedule}",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Campo nombre
            OutlinedTextField(
                value         = state.value.userName,
                onValueChange = {
                    viewModel.onEvent(BookingEvent.OnUserNameChanged(it))
                },
                label          = { Text("Tu nombre") },
                isError        = state.value.userNameError != null,
                supportingText = state.value.userNameError?.let { { Text(it) } },
                singleLine     = true,
                modifier       = Modifier.fillMaxWidth()
            )

            // Selector de asientos
            Text("Número de asientos", fontWeight = FontWeight.SemiBold)
            Row(
                verticalAlignment    = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                IconButton(
                    onClick  = {
                        if (state.value.seats > 1)
                            viewModel.onEvent(
                                BookingEvent.OnSeatsChanged(state.value.seats - 1)
                            )
                    }
                ) {
                    Icon(Icons.Filled.Remove, contentDescription = "Menos")
                }
                Text(
                    text     = "${state.value.seats}",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                IconButton(
                    onClick = {
                        if (state.value.seats < 10)
                            viewModel.onEvent(
                                BookingEvent.OnSeatsChanged(state.value.seats + 1)
                            )
                    }
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Más")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text  = "× $${state.value.pricePerSeat} c/u",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Resumen del precio
            Row(
                modifier              = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment     = Alignment.CenterVertically
            ) {
                Text("Total a pagar", fontSize = 16.sp)
                Text(
                    text       = "$${"%.2f".format(
                        state.value.seats * state.value.pricePerSeat
                    )}",
                    fontSize   = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color      = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}