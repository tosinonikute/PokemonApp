package com.pokemon.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pokemon.presentation.state.PokemonDetailPresentationState.Error
import com.pokemon.presentation.state.PokemonDetailPresentationState.Success
import com.pokemon.presentation.state.PokemonDetailPresentationState.Loading
import com.pokemon.presentation.viewmodel.PokemonDetailViewModel
import com.pokemon.ui.R
import com.pokemon.ui.mapper.PokemonDetailPresentationToUiMapper
import com.pokemon.ui.model.PokemonInfoDetailUiModel

@Composable
fun PokemonDetailScreen(
    pokemonDetailViewModel: PokemonDetailViewModel,
    detailUiMapper: PokemonDetailPresentationToUiMapper,
    onBackClick: () -> Unit
) {
    val presentationDetailState = pokemonDetailViewModel.pokemonDetailPresentationState.collectAsState(
        Loading
    ).value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        when (presentationDetailState) {
            is Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(48.dp)
                        .align(Alignment.Center),
                    color = Color.Black
                )
            }
            is Success -> {
                PokemonDetailCard(
                    uiModel = detailUiMapper.map(presentationDetailState.pokemonDetail),
                    onBackClick = onBackClick
                )
            }
            is Error -> {
                ErrorDetailLayout(
                    message = stringResource(R.string.no_pokemon_fetched),
                    onBackClick = onBackClick,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
private fun PokemonDetailCard(
    uiModel: PokemonInfoDetailUiModel,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .size(48.dp)
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Go Back",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            Text(
                text = uiModel.name,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(horizontal = (LocalConfiguration.current.screenWidthDp * 0.1).dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(0.8f)
                    .aspectRatio(1f)
                    .background(
                        MaterialTheme.colorScheme.background,
                        CircleShape
                    )
                    .alpha(0.4f)
            )

            AsyncImage(
                model = uiModel.imageUrl,
                contentDescription = "Pokemon",
                modifier = Modifier
                    .fillMaxSize(0.8f)
                    .aspectRatio(1f),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = uiModel.name,
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            PokemonStatRow(
                label = "Height",
                value = "${uiModel.height / 10.0} m"
            )
        }

        Spacer(modifier = Modifier.height(48.dp))
    }
}

@Composable
private fun PokemonStatRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1f)
        )
    }
}
