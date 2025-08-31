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
import androidx.navigation.toRoute
import com.pokemon.main.navigateSingleTop
import com.pokemon.presentation.viewmodel.PokemonDetailViewModel
import com.pokemon.ui.mapper.PokemonDetailPresentationToUiMapper
import com.pokemon.ui.mapper.PokemonPresentationToUiMapper
import com.pokemon.ui.view.PokemonDetailScreen
import com.pokemon.ui.view.PokemonListScreen

@Composable
fun App(
    uiMapper: PokemonPresentationToUiMapper,
    detailUiMapper: PokemonDetailPresentationToUiMapper
) {
    val navController = rememberNavController()
    val pokemonListViewModel = hiltViewModel<PokemonListViewModel>()
    val pokemonDetailViewModel = hiltViewModel<PokemonDetailViewModel>()
    NavHost(
        navController,
        startDestination = Routes.Home::class,
        modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        navigation<Routes.Home>(startDestination = Routes.List::class) {
            composableNoAnimation<Routes.List> {
                pokemonListViewModel.onLoadPokemonList()
                PokemonListScreen(
                    viewModel = pokemonListViewModel,
                    onPokemonClick = { pokemonId ->
                        navController.navigateSingleTop(Routes.Detail(pokemonId))
                    },
                    onRetry = { pokemonListViewModel.onLoadPokemonList() },
                    uiMapper = uiMapper
                )
            }
        }
        navigation<Routes.DetailGraph>(startDestination = Routes.Detail::class) {
            composableNoAnimation<Routes.Detail> { backStackEntry ->
                val route: Routes.Detail = backStackEntry.toRoute()
                pokemonDetailViewModel.onGetPokemonDetail(route.pokemonId ?: 0)
                PokemonDetailScreen(
                    pokemonDetailViewModel = pokemonDetailViewModel,
                    detailUiMapper = detailUiMapper,
                    onBackClick = { navController.popBackStack()  }
                )
            }
        }
    }
}
