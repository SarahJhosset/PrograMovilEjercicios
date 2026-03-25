package com.ucb.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ucb.app.github.presentation.screen.GithubScreen
import com.ucb.app.movie.presentation.screen.MovieDetailScreen
import com.ucb.app.movie.presentation.screen.MovieScreen

@Composable
fun AppNavHost() {


    val navController = rememberNavController()


    // Dentro de tu AppNavHost.kt
    NavHost(navController = navController, startDestination = "movies") {
        composable("movies") {
            MovieScreen(
                onMovieClick = { title ->
                    // El NavHost escucha el título y ejecuta la navegación real
                    navController.navigate("detail/$title")
                }
            )
        }

        composable("detail/{title}") { backStackEntry ->
            // 1. Extraemos el título de la "bolsa de viaje" (arguments)
            val movieTitle = backStackEntry.arguments?.getString("title") ?: "Sin Nombre"

            // 2. LLAMAMOS A LA PANTALLA (Para que no salga en blanco)
            MovieDetailScreen(
                title = movieTitle,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
