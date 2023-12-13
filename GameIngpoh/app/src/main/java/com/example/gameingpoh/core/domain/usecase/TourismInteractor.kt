package com.example.gameingpoh.core.domain.usecase

import com.example.gameingpoh.core.data.GameRepository
import com.example.gameingpoh.core.domain.model.Game
import com.example.gameingpoh.core.domain.repository.IGameRepository

class GameInteractor(private val gameRepository: IGameRepository): GameUseCase {
    override fun getAllGame() = gameRepository.getAllGame()

    override fun getFavoriteGame() = gameRepository.getFavoriteGame()

    override fun setFavoriteGame(tourism: Game, state: Boolean) = gameRepository.setFavoriteGame(tourism, state)

}