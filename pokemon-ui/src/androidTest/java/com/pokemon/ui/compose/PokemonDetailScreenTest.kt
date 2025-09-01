package com.pokemom.ui.compose

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pokemon.presentation.model.PokemonInfoDetailPresentationModel
import com.pokemon.presentation.state.PokemonDetailPresentationState
import com.pokemon.presentation.util.ImageConstants
import com.pokemon.presentation.viewmodel.PokemonDetailViewModel
import com.pokemon.ui.mapper.PokemonDetailPresentationToUiMapper
import com.pokemon.ui.model.PokemonInfoDetailUiModel
import com.pokemon.ui.view.PokemonDetailScreen
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PokemonDetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val detailMockViewModel = mockk<PokemonDetailViewModel>(relaxed = true)
    private val detailMockMapper = mockk<PokemonDetailPresentationToUiMapper>(relaxed = true)
    private val detailMockOnBackClick = mockk<() -> Unit>(relaxed = true)

    @Test
    fun pokemonDetailScreen_showsPokemonDetail() {
        // Given
        val pokemonId = 1
        val mockPokemonDetail = PokemonInfoDetailPresentationModel(
            name = "Pikachu",
            id = 1,
            imageUrl = "${ImageConstants.POKEMON_IMAGE_BASE_URL}1.png",
            height = 40
        )
        val detailSuccessState = PokemonDetailPresentationState.Success(mockPokemonDetail)
        val detailSuccessStateFlow = MutableStateFlow<PokemonDetailPresentationState>(detailSuccessState)
        val detailMockUiModel = PokemonInfoDetailUiModel(
            name = "Pikachu",
            id = 1,
            imageUrl = "${ImageConstants.POKEMON_IMAGE_BASE_URL}1.png",
            height = 40
        )
        every { detailMockViewModel.pokemonDetailPresentationState } returns detailSuccessStateFlow
        every { detailMockMapper.map(mockPokemonDetail) } returns detailMockUiModel

        // When
        detailMockViewModel.onGetPokemonDetail(pokemonId)
        composeTestRule.setContent {
            PokemonDetailScreen(
                pokemonDetailViewModel = detailMockViewModel,
                detailUiMapper = detailMockMapper,
                onBackClick = detailMockOnBackClick
            )
        }

        // Then
        composeTestRule.onAllNodesWithText("Pikachu").assertCountEquals(2)
        composeTestRule.onNodeWithText("4.0 m").assertExists()
    }
}
