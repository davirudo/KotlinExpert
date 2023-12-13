package com.example.gameingpoh.core.utils

import com.example.gameingpoh.core.data.source.local.entity.GameEntity
import com.example.gameingpoh.core.data.source.remote.response.GameResponse
import com.example.gameingpoh.core.domain.model.Game

object DataMapper {
    fun mapResponseToEntities(input : List<GameResponse>): List<GameEntity> {
        val gameList = ArrayList<GameEntity>()
        input.map {
            val game = GameEntity(
                id = it.id,
                name = it.name,
                backgroundImage = it.backgroundImage,
                released = it.released
            )
            gameList.add(game)
        }
        return gameList
    }

    fun mapEntitiesToDomain(input: List<GameEntity>): List<Game> =
        input.map {
            Game(
                id = it.id,
                name = it.name,
                backgroundImage = it.backgroundImage,
                released = it.released
            )
        }

    fun mapDomainToEntity(input : Game) = GameEntity(
        id = input.id,
        name = input.name,
        backgroundImage = input.backgroundImage,
        released = input.released
    )
}