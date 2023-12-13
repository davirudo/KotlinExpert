package com.example.gameingpoh.core.data

import com.example.gameingpoh.core.data.source.local.LocalDataSource
import com.example.gameingpoh.core.data.source.remote.RemoteDataSource
import com.example.gameingpoh.core.data.source.remote.network.ApiResponse
import com.example.gameingpoh.core.data.source.remote.response.GameResponse
import com.example.gameingpoh.core.domain.model.Game
import com.example.gameingpoh.core.domain.repository.IGameRepository
import com.example.gameingpoh.core.utils.AppExecutor
import com.example.gameingpoh.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutor: AppExecutor
) : IGameRepository {

    companion object {
        @Volatile
        private var instance: GameRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutor
        ): GameRepository =
            instance ?: synchronized(this) {
                instance ?: GameRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllGame(): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<GameResponse>>() {
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.getAllGame().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Game>?): Boolean =
//                data == null || data.isEmpty()
                true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> =
                remoteDataSource.getAllTourism()

            override suspend fun saveCallResult(data: List<GameResponse>) {
                val tourismList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertGame(tourismList)
            }
        }.asFlow()

    override fun getFavoriteGame(): Flow<List<Game>> {
        return localDataSource.getFavoriteGame().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteGame(game: Game, state: Boolean) {
        val gameEntity = DataMapper.mapDomainToEntity(game)
        appExecutor.diskIO().execute { localDataSource.setFavoriteGame(gameEntity, state) }
    }
}