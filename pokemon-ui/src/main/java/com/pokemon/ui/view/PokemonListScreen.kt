package com.pokemon.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pokemon.presentation.state.PokemonPresentationState.Loading
import com.pokemon.presentation.state.PokemonPresentationState.Error
import com.pokemon.presentation.state.PokemonPresentationState.Success
import com.pokemon.presentation.viewmodel.PokemonListViewModel
import com.pokemon.ui.R
import com.pokemon.ui.mapper.PokemonPresentationToUiMapper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel,
    onPokemonClick: (Int) -> Unit,
    onRetry: () -> Unit,
    onLoadMore: () -> Unit,
    uiMapper: PokemonPresentationToUiMapper
) {
    val presentationState by viewModel.pokemonPresentationState.collectAsStateWithLifecycle(Loading)
    val gridState = rememberLazyGridState()

    LaunchedEffect(gridState) {
        snapshotFlow { gridState.layoutInfo }
            .collect { layoutInfo ->
                val lastVisibleIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index
                val totalItems = layoutInfo.totalItemsCount

                // Trigger load more when user is near the end (within 1 item)
                if (lastVisibleIndex != null && lastVisibleIndex >= totalItems - 1) {
                    onLoadMore()
                }
            }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.app_name))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (presentationState) {
                is Loading -> {
                    val contentDescr = stringResource(R.string.loading_description)
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(48.dp)
                            .align(Alignment.Center)
                            .semantics { contentDescription = contentDescr },
                    )
                }

                is Success -> {
                    val uiList = (presentationState as Success).pokemonList.map { uiMapper.map(it) }
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        state = gridState,
                        contentPadding = PaddingValues(
                            start = 8.dp,
                            top = 16.dp,
                            end = 8.dp,
                            bottom = 88.dp
                        ),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(
                            items = uiList,
                            key = { it.id }
                        ) { pokemon ->
                            PokemonCard(
                                pokemon = pokemon,
                                onClick = { onPokemonClick(pokemon.id) }
                            )
                        }
                    }
                }

                is Error -> {
                    ErrorLayout(
                        message = stringResource(R.string.no_pokemon_fetched),
                        onRetry = onRetry,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}
