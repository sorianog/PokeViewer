package com.sorianog.pokeviewer.di

import com.sorianog.pokeviewer.data.AppConstants
import com.sorianog.pokeviewer.data.PokemonRepository
import com.sorianog.pokeviewer.data.api.PokemonApiService
import com.sorianog.pokeviewer.data.datasource.PokemonDataSource
import com.sorianog.pokeviewer.data.datasource.PokemonDataSourceImpl
import com.sorianog.pokeviewer.data.datasource.PokemonPagingSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(Json {
                ignoreUnknownKeys = true
            }.asConverterFactory("application/json".toMediaType()))
            .baseUrl(AppConstants.API_BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): PokemonApiService {
        return retrofit.create(PokemonApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesPokemonDataSource(apiService: PokemonApiService): PokemonDataSource {
        return PokemonDataSourceImpl(apiService)
    }

    @Provides
    @Singleton
    fun providesPokemonRepository(pokemonDataSource: PokemonDataSource): PokemonRepository {
        return PokemonRepository(pokemonDataSource)
    }

    @Provides
    @Singleton
    fun providesPokemonPagingSource(repository: PokemonRepository): PokemonPagingSource {
        return PokemonPagingSource(repository)
    }
}