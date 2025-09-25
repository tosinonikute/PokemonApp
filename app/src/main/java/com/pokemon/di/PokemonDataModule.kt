package com.pokemon.di

import com.pokemon.data.datasource.PokemonDataSource
import com.pokemon.data.mapper.PokemonDataToDomainMapper
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
        pokemonInfoDetailModelToDomainMapper: PokemonInfoDetailModelToDomainMapper,
        pokemonDataToDomainMapper: PokemonDataToDomainMapper
    ): PokemonRepository = PokemonDataRepository(
        pokemonDataSource,
        pokemonInfoDetailModelToDomainMapper,
        pokemonDataToDomainMapper
    )

    @Provides
    fun providerPokemonDataToDomainMapper(
        pokemonInfoDataModelToDomainMapper: PokemonInfoDataModelToDomainMapper
    ) = PokemonDataToDomainMapper(pokemonInfoDataModelToDomainMapper)

    @Provides
    fun providerPokemonInfoDataModelToDomainMapper(
    ) = PokemonInfoDataModelToDomainMapper()

    @Provides
    fun providerPokemonInfoDetailModelToDomainMapper(
    ) = PokemonInfoDetailModelToDomainMapper()
}
