package com.example.gameingpoh.core.domain.usecase

import com.example.gameingpoh.core.data.Resource
import com.example.gameingpoh.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getAllGame(): Flow<Resource<List<Game>>>
    fun getFavoriteGame(): Flow<List<Game>>
    fun setFavoriteGame(tourism: Game, state: Boolean)

}