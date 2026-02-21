package com.ucb.app.di

import androidx.lifecycle.viewmodel.compose.viewModel
import com.ucb.app.counter.presentation.viewmodel.CounterViewModel
import com.ucb.app.increment.presentation.viewmodel.IncrementViewModel
import com.ucb.app.nm.login.presentation.viewmodel.LoginViewModel
import com.ucb.app.product_detail.presentation.screen.ProductDetailScreen
import com.ucb.app.product_detail.presentation.viewmodel.ProductDetailViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {

    viewModelOf(
        ::ProductDetailViewModel)
    viewModelOf(::CounterViewModel)
    viewModelOf(::IncrementViewModel)
    viewModelOf(::LoginViewModel)
}