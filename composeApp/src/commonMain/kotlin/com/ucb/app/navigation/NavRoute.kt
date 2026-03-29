package com.ucb.app.navigation

import kotlinx.serialization.Serializable


@Serializable
sealed class NavRoute {


    @Serializable
    object Profile: NavRoute()


    @Serializable
    object ProfileEdit: NavRoute()

    @Serializable
    object Github: NavRoute()

    @Serializable
    object Movies: NavRoute()

    @Serializable
    data class MovieDetail(val title: String): NavRoute()

    @Serializable
    data class Booking(val movieTitle: String, val schedule: String): NavRoute()

    @Serializable
    object Crypto: NavRoute()

    @Serializable
    object FakeStore: NavRoute()

    @Serializable
    object CountryStore: NavRoute()
}