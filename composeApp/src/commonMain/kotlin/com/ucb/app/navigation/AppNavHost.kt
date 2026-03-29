package com.ucb.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ucb.app.booking.presentation.screen.BookingScreen
import com.ucb.app.country.presentation.screen.CountryScreen
import com.ucb.app.crypto.presentation.screen.CryptoScreen
import com.ucb.app.fakestore.presentation.screen.StoreScreen
import com.ucb.app.github.presentation.screen.GithubScreen
import com.ucb.app.movie.presentation.screen.MovieDetailScreen
import com.ucb.app.movie.presentation.screen.MovieScreen

@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoute.Crypto) {
        composable<NavRoute.Profile> {

        }

        composable<NavRoute.ProfileEdit> {

        }
        composable<NavRoute.Github> {
            GithubScreen()
        }
        composable<NavRoute.Movies> {
            MovieScreen(
                onMovieClick = { title ->
                    navController.navigate(NavRoute.MovieDetail(title))
                }
            )
        }
        composable<NavRoute.MovieDetail> { backStackEntry ->
            val movieDetail: NavRoute.MovieDetail = backStackEntry.toRoute()
            MovieDetailScreen(
                title = movieDetail.title,
                onBack = { navController.popBackStack() },
                onBookTicket = { title, schedule ->
                    navController.navigate(NavRoute.Booking(title, schedule))
                }
            )
        }
        composable<NavRoute.Booking> { backStackEntry ->
            val booking: NavRoute.Booking = backStackEntry.toRoute()
            BookingScreen(
                movieTitle = booking.movieTitle,
                schedule = booking.schedule,
                navController = navController
            )
        }
        composable<NavRoute.Crypto> {
            CryptoScreen()
        }
        composable<NavRoute.FakeStore> {
            StoreScreen()
        }
        composable<NavRoute.CountryStore> {
            CountryScreen()
        }
    }
}
