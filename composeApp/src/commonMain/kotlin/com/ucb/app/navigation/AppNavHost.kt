package com.ucb.app.navigation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ucb.app.booking.presentation.screen.BookingScreen
import com.ucb.app.movie.presentation.screen.MovieDetailScreen
import com.ucb.app.movie.presentation.screen.MovieScreen
import com.ucb.app.nm.login.presentation.screen.LoginScreen

@Composable
fun AppNavHost(snackbarHostState: SnackbarHostState) {


    val navController = rememberNavController()


    // Dentro de tu AppNavHost.kt
    NavHost(navController = navController, startDestination = "movies") {
        //NavHost(navController = navController, startDestination = "movies") {
        // si se quiere cambiar lo q se va a ver solo cambiamos startDestination

        // Login
        composable("login") {
            LoginScreen(
                snackbarHostState = snackbarHostState
            )
        }

        // Movie
        composable("movies") {
            MovieScreen(
                onMovieClick = { title ->
                    // El NavHost escucha el título y ejecuta la navegación real
                    navController.navigate("detail/$title")
                }
            )
        }
        // Detalle de movie
        composable("detail/{title}") { backStackEntry ->
            // 1. Extraemos el título de la "bolsa de viaje" (arguments)
            val movieTitle = backStackEntry.arguments?.getString("title") ?: "Sin Nombre"

            // 2. LLAMAMOS A LA PANTALLA (Para que no salga en blanco)
            MovieDetailScreen(
                title = movieTitle,
                onBack = { navController.popBackStack() },
                onBookTicket = { title, schedule ->
                    navController.navigate("booking/$title/$schedule")
                }
            )
        }

        //Entradas Booking
        composable("booking/{title}/{schedule}") { backStackEntry ->
            val title    = backStackEntry.arguments?.getString("title")    ?: ""
            val schedule = backStackEntry.arguments?.getString("schedule") ?: ""
            BookingScreen(
                movieTitle    = title,
                schedule      = schedule,
                navController = navController
            )
        }
    }
}
