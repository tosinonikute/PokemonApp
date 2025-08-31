package com.pokemon.di

import com.pokemon.ui.mapper.PokemonDetailPresentationToUiMapper
import com.pokemon.ui.mapper.PokemonPresentationToUiMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PokemonPresentationModule {

    @Provides
    fun providerPokemonPresentationToUiMapper(
    ) = PokemonPresentationToUiMapper()

    @Provides
    fun providerPokemonDetailPresentationToUiMapper(
    ) = PokemonDetailPresentationToUiMapper()
}
