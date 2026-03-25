package com.ucb.app.di

import com.ucb.app.booking.data.datasource.BookingRemoteDatasource
import com.ucb.app.booking.data.datasource.BookingService
import com.ucb.app.booking.data.repository.BookingRepositoryImpl
import com.ucb.app.booking.domain.repository.BookingRepository
import com.ucb.app.github.data.datasource.GithubRemoteDataSource
import com.ucb.app.github.data.repository.GithubRepositoryImpl
import com.ucb.app.github.data.service.GitHubApiService
import com.ucb.app.github.domain.repository.GithubRepository
import com.ucb.app.login.data.repository.LoginRepositoryImpl
import com.ucb.app.login.domain.repository.AuthenticationRepository
import com.ucb.app.movie.data.datasource.MovieRemoteDatasource
import com.ucb.app.movie.data.repository.MovieRepositoryImpl
import com.ucb.app.movie.data.service.MovieService
import com.ucb.app.movie.domain.repository.MovieRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    singleOf(::GitHubApiService).bind<GithubRemoteDataSource>()
    singleOf(::GithubRepositoryImpl).bind<GithubRepository>()
    singleOf(::MovieRepositoryImpl).bind<MovieRepository>()
    singleOf(::MovieService).bind<MovieRemoteDatasource>()


    single<AuthenticationRepository> { LoginRepositoryImpl() }
    single<BookingRemoteDatasource> { BookingService() }
    single<BookingRepository> { BookingRepositoryImpl(get()) }
}