package com.example.gameingpoh.core.di

import androidx.room.Room
import com.example.gameingpoh.core.data.GameRepository
import com.example.gameingpoh.core.data.source.local.LocalDataSource
import com.example.gameingpoh.core.data.source.local.room.GameDatabase
import com.example.gameingpoh.core.data.source.remote.RemoteDataSource
import com.example.gameingpoh.core.data.source.remote.network.ApiService
import com.example.gameingpoh.core.domain.repository.IGameRepository
import com.example.gameingpoh.core.utils.AppExecutor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<GameDatabase>().gameDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            GameDatabase::class.java, "Game.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {(key : String) ->
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                val url = request.url.newBuilder()
                    .addQueryParameter("key", "af0d00844b3e4675ac9dc61cbd264ffc")
                    .build()
                val newRequest = request.newBuilder().url(url).build()
                chain.proceed(newRequest)
            }
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.rawg.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutor() }
    single<IGameRepository> { GameRepository(get(), get(), get()) }
}