package com.pokemon.di

import com.pokemon.domain.repository.PokemonRepository
import com.pokemon.domain.usecase.GetPokemonListUseCase
import com.pokemon.presentation.mapper.PokemonDomainToPresentationMapper
import com.pokemon.ui.mapper.PokemonPresentationToUiMapper
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
    fun providerPokemonDomainToPresentationMapper(
    ) = PokemonDomainToPresentationMapper()
}
