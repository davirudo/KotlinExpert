//package com.example.gameingpoh.core.di
//
//import android.content.Context
//import com.example.gameingpoh.core.data.GameRepository
//import com.example.gameingpoh.core.data.source.local.LocalDataSource
//import com.example.gameingpoh.core.data.source.local.room.GameDatabase
//import com.example.gameingpoh.core.data.source.remote.RemoteDataSource
//import com.example.gameingpoh.core.data.source.remote.network.ApiConfig
//import com.example.gameingpoh.core.domain.repository.IGameRepository
//import com.example.gameingpoh.core.domain.usecase.GameInteractor
//import com.example.gameingpoh.core.domain.usecase.GameUseCase
//import com.example.gameingpoh.core.utils.AppExecutor
//
//object Injection {
//    private fun provideRepository(context: Context): IGameRepository {
//        val database = GameDatabase.getInstance(context)
//
//        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
//        val localDataSource = LocalDataSource.getInstance(database.gameDao())
//        val appExecutors = AppExecutor()
//
//        return GameRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
//    }
//
//    fun provideTourismUseCase(context: Context): GameUseCase {
//        val repository = provideRepository(context)
//        return GameInteractor(repository)
//    }
//}