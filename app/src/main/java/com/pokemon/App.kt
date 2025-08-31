package com.pokemon

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.navigation
import com.pokemon.main.Routes
import com.pokemon.main.composableNoAnimation
import com.pokemon.presentation.viewmodel.PokemonListViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.pokemon.ui.mapper.PokemonPresentationToUiMapper
import com.pokemon.ui.view.PokemonListScreen

@Composable
fun App(
    uiMapper: PokemonPresentationToUiMapper
) {
    val navController = rememberNavController()
    val pokemonListViewModel = hiltViewModel<PokemonListViewModel>()
    NavHost(
        navController,
        startDestination = Routes.Home::class,
        modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        navigation<Routes.Home>(startDestination = Routes.List::class) {
            composableNoAnimation<Routes.List> {
                PokemonListScreen(
                    viewModel = pokemonListViewModel,
                    onPokemonClick = {},
                    onRetry = {},
                    uiMapper = uiMapper
                )
            }
            composableNoAnimation<Routes.Detail> {

            }
        }
    }
}
