package com.ucb.app.di

import androidx.lifecycle.viewmodel.compose.viewModel
import com.ucb.app.product_detail.presentation.screen.ProductDetailScreen
import com.ucb.app.product_detail.presentation.viewmodel.ProductDetailViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {

    viewModelOf(
        ::ProductDetailViewModel)
}