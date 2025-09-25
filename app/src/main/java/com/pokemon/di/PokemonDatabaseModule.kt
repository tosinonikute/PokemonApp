package com.pokemon.di

import android.app.Application
import androidx.room.Room
import com.pokemon.datasource.api.datasource.AppDatabase
import com.pokemon.datasource.api.datasource.PokemonDao
import com.pokemon.datasource.api.mapper.PokemonInfoModelApiToDatabaseMapper
import com.pokemon.datasource.api.mapper.PokemonInfoModelDatabaseToDataMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PokemonDatabaseModule {

    @Provides
    @Singleton
    fun providerPokemonInfoModelApiToDatabaseMapper(
    ) = PokemonInfoModelApiToDatabaseMapper()

    @Provides
    @Singleton
    fun providerPokemonInfoModelDatabaseToDataMapper(
    ) = PokemonInfoModelDatabaseToDataMapper()

    @Provides
    @Singleton
    fun providesDb(context: Application) =
        Room.databaseBuilder(context, AppDatabase::class.java, "pokemon.db").build()

    @Provides
    @Singleton
    fun providePokemonDao(appDatabase: AppDatabase): PokemonDao = appDatabase.pokemonDao()

}
