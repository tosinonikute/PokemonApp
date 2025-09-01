package com.pokemom.ui.compose

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pokemon.presentation.model.PokemonInfoPresentationModel
import com.pokemon.presentation.state.PokemonPresentationState
import com.pokemon.presentation.util.ImageConstants
import com.pokemon.presentation.viewmodel.PokemonListViewModel
import com.pokemon.ui.mapper.PokemonPresentationToUiMapper
import com.pokemon.ui.model.PokemonInfoUiModel
import com.pokemon.ui.view.PokemonListScreen
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PokemonListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockViewModel = mockk<PokemonListViewModel>(relaxed = true)
    private val mockMapper = mockk<PokemonPresentationToUiMapper>(relaxed = true)
    private val mockOnPokemonClick = mockk<(Int) -> Unit>(relaxed = true)
    private val mockOnRetry = mockk<() -> Unit>(relaxed = true)

    @Test
    fun pokemonListScreen_showsLoadingState() {
        // Given
        val loadingStateFlow = MutableStateFlow<PokemonPresentationState>(
            PokemonPresentationState.Loading
        )
        every { mockViewModel.pokemonPresentationState } returns loadingStateFlow

        // When
        composeTestRule.setContent {
            PokemonListScreen(
                viewModel = mockViewModel,
                onPokemonClick = mockOnPokemonClick,
                onRetry = mockOnRetry,
                uiMapper = mockMapper
            )
        }

        // Then
        composeTestRule.onNodeWithContentDescription("Loading")
            .assertExists()
        composeTestRule.onNodeWithText("Pokemon")
            .assertExists()
    }

    @Test
    fun pokemonListScreen_showsPokemonList_whenSuccess() {
        // Given
        val mockPokemonList = listOf(
            PokemonInfoPresentationModel(
                name = "Pikachu",
                id = 1,
                imageUrl = "${ImageConstants.POKEMON_IMAGE_BASE_URL}1.png"
            ),
            PokemonInfoPresentationModel(
                name = "Charizard",
                id = 2,
                imageUrl = "${ImageConstants.POKEMON_IMAGE_BASE_URL}2.png"
            ),
            PokemonInfoPresentationModel(
                name = "Blastoise",
                id = 3,
                imageUrl = "${ImageConstants.POKEMON_IMAGE_BASE_URL}3.png"
            )
        )

        val successState = PokemonPresentationState.Success(mockPokemonList)
        val successStateFlow = MutableStateFlow<PokemonPresentationState>(successState)

        every { mockViewModel.pokemonPresentationState } returns successStateFlow
        every { mockMapper.map(any()) } returnsMany listOf(
            PokemonInfoUiModel(
                name = "Pikachu",
                id = 1,
                imageUrl = "${ImageConstants.POKEMON_IMAGE_BASE_URL}1.png"
            ),
            PokemonInfoUiModel(
                name = "Charizard",
                id = 2,
                imageUrl = "${ImageConstants.POKEMON_IMAGE_BASE_URL}2.png"
            ),
            PokemonInfoUiModel(
                name = "Blastoise",
                id = 3,
                imageUrl = "${ImageConstants.POKEMON_IMAGE_BASE_URL}3.png"
            )
        )

        // When
        composeTestRule.setContent {
            PokemonListScreen(
                viewModel = mockViewModel,
                onPokemonClick = mockOnPokemonClick,
                onRetry = mockOnRetry,
                uiMapper = mockMapper
            )
        }

        // Then
        composeTestRule.onNodeWithText("Pikachu").assertExists()
        composeTestRule.onNodeWithText("Charizard").assertExists()
        composeTestRule.onNodeWithText("Blastoise").assertExists()
        composeTestRule.onNodeWithText("#1").assertExists()
        composeTestRule.onNodeWithText("#2").assertExists()
        composeTestRule.onNodeWithText("#3").assertExists()
    }

    @Test
    fun pokemonListScreen_showsErrorState() {
        // Given
        val errorState = PokemonPresentationState.Error
        val errorStateFlow = MutableStateFlow<PokemonPresentationState>(errorState)
        every { mockViewModel.pokemonPresentationState } returns errorStateFlow

        // When
        composeTestRule.setContent {
            PokemonListScreen(
                viewModel = mockViewModel,
                onPokemonClick = mockOnPokemonClick,
                onRetry = mockOnRetry,
                uiMapper = mockMapper
            )
        }

        // Then
        composeTestRule.onNodeWithText("No pokemon data fetched")
            .assertExists()
        composeTestRule.onNodeWithText("Retry")
            .assertExists()
    }

    @Test
    fun pokemonCard_callsOnClickWhenTapped() {
        // Given
        val mockPokemonList = listOf(
            PokemonInfoPresentationModel(
                name = "Pikachu",
                id = 1,
                imageUrl = "${ImageConstants.POKEMON_IMAGE_BASE_URL}1.png"
            ),
            PokemonInfoPresentationModel(
                name = "Charizard",
                id = 2,
                imageUrl = "${ImageConstants.POKEMON_IMAGE_BASE_URL}2.png"
            )
        )

        val successState = PokemonPresentationState.Success(mockPokemonList)
        val successStateFlow = MutableStateFlow<PokemonPresentationState>(successState)

        every { mockViewModel.pokemonPresentationState } returns successStateFlow
        every { mockMapper.map(any()) } returnsMany listOf(
            PokemonInfoUiModel(
                name = "Pikachu",
                id = 1,
                imageUrl = "${ImageConstants.POKEMON_IMAGE_BASE_URL}1.png"
            ),
            PokemonInfoUiModel(
                name = "Charizard",
                id = 2,
                imageUrl = "${ImageConstants.POKEMON_IMAGE_BASE_URL}2.png"
            )
        )

        // When
        composeTestRule.setContent {
            PokemonListScreen(
                viewModel = mockViewModel,
                onPokemonClick = mockOnPokemonClick,
                onRetry = mockOnRetry,
                uiMapper = mockMapper
            )
        }

        // Then
        composeTestRule.onNodeWithText("Pikachu").performClick()
        verify { mockOnPokemonClick(1) }
    }
}
