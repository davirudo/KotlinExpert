package com.example.gameingpoh.detail

import androidx.lifecycle.ViewModel
import com.example.gameingpoh.core.domain.model.Game
import com.example.gameingpoh.core.domain.usecase.GameUseCase

class DetailGameViewModel (private val tourismUseCase: GameUseCase) : ViewModel() {
    fun setFavoriteGame(game: Game, newStatus:Boolean) =
        tourismUseCase.setFavoriteGame(game, newStatus)
}