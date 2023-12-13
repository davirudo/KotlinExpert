package com.example.gameingpoh.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.gameingpoh.core.domain.usecase.GameUseCase

class HomeViewModel(tourismUseCase: GameUseCase) : ViewModel() {
    val game = tourismUseCase.getAllGame().asLiveData()
}