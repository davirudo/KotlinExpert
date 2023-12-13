package com.example.gameingpoh.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.gameingpoh.core.domain.usecase.GameUseCase


class FavoriteViewModel(gameuseCase: GameUseCase) : ViewModel() {
    val favoriteGame = gameuseCase.getFavoriteGame().asLiveData()
}
