package com.example.gameingpoh.di

import com.example.gameingpoh.core.domain.usecase.GameInteractor
import com.example.gameingpoh.core.domain.usecase.GameUseCase
import com.example.gameingpoh.favorite.FavoriteViewModel
import com.example.gameingpoh.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GameUseCase> { GameInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}