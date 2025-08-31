package com.pokemon.di

import com.pokemon.data.datasource.PokemonDataSource
import com.pokemon.data.mapper.PokemonInfoDataModelToDomainMapper
import com.pokemon.data.mapper.PokemonInfoDetailModelToDomainMapper
import com.pokemon.data.repository.PokemonDataRepository
import com.pokemon.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PokemonDataModule {

    @Provides
    fun providerPokemonApiToDataMapper(
        pokemonDataSource: PokemonDataSource,
        pokemonInfoDataModelToDomainMapper: PokemonInfoDataModelToDomainMapper,
        pokemonInfoDetailModelToDomainMapper: PokemonInfoDetailModelToDomainMapper
    ): PokemonRepository = PokemonDataRepository(
        pokemonDataSource,
        pokemonInfoDataModelToDomainMapper,
        pokemonInfoDetailModelToDomainMapper
    )

    @Provides
    fun providerPokemonInfoDataModelToDomainMapper(
    ) = PokemonInfoDataModelToDomainMapper()

    @Provides
    fun providerPokemonInfoDetailModelToDomainMapper(
    ) = PokemonInfoDetailModelToDomainMapper()
}
