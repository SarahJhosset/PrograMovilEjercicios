package com.ucb.app.movie.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.ucb.app.movie.presentation.viewmodel.MovieViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    title: String,
    onBack: () -> Unit,
    onBookTicket: (String, String) -> Unit,
    viewModel: MovieViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsState().value
    val movie = state.list.find { it.title == title }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle de Película") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        movie?.let { movie ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Imagen Grande
                AsyncImage(
                    model = movie.pathUrl,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().height(400.dp),
                    contentScale = ContentScale.Crop
                )

                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = movie.title, style = MaterialTheme.typography.headlineMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = movie.description, style = MaterialTheme.typography.bodyLarge)

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Horarios disponibles:", fontWeight = FontWeight.Bold)
                    // Simulación de horarios
                    Text(text = "14:00 - 17:30 - 21:00", color = MaterialTheme.colorScheme.primary)

                    Spacer(modifier = Modifier.height(32.dp))

                    val selectedSchedule = "14:00" // Simulación de horario seleccionado

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            onBookTicket(movie.title, selectedSchedule)
                        }
                    ) {
                        Text("Comprar entrada")
                    }
                }
            }
        }
    }
}
