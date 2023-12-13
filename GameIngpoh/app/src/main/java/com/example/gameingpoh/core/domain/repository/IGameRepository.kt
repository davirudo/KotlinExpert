package com.example.gameingpoh.core.domain.repository

import com.example.gameingpoh.core.data.Resource
import com.example.gameingpoh.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface IGameRepository {

    fun getAllGame(): Flow<Resource<List<Game>>>

    fun getFavoriteGame(): Flow<List<Game>>

    fun setFavoriteGame(game: Game, state: Boolean)
}