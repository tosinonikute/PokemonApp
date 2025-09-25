package com.pokemon.di

import com.pokemon.domain.repository.PokemonRepository
import com.pokemon.domain.usecase.GetPokemonDetailUseCase
import com.pokemon.domain.usecase.GetPokemonListUseCase
import com.pokemon.presentation.mapper.PokemonDetailDomainToPresentationMapper
import com.pokemon.presentation.mapper.PokemonDomainToPresentationMapper
import com.pokemon.presentation.mapper.PokemonInfoDomainToPresentationMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PokemonDomainModule {

    @Provides
    @Singleton
    fun provideGetPokemonListUseCase(
        pokemonRepository: PokemonRepository
    ): GetPokemonListUseCase {
        return GetPokemonListUseCase(pokemonRepository)
    }

    @Provides
    @Singleton
    fun provideGetPokemonDetailUseCase(
        pokemonRepository: PokemonRepository
    ): GetPokemonDetailUseCase {
        return GetPokemonDetailUseCase(pokemonRepository)
    }

    @Provides
    fun providerPokemonInfoDomainToPresentationMapper(
    ) = PokemonInfoDomainToPresentationMapper()

    @Provides
    fun providerPokemonDetailDomainToPresentationMapper(
    ) = PokemonDetailDomainToPresentationMapper()

    @Provides
    fun providerPokemonDomainToPresentationMapper(
        pokemonInfoDomainToPresentationMapper: PokemonInfoDomainToPresentationMapper
    ) = PokemonDomainToPresentationMapper(pokemonInfoDomainToPresentationMapper)
}
