package com.pokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.pokemon.ui.mapper.PokemonDetailPresentationToUiMapper
import com.pokemon.ui.mapper.PokemonPresentationToUiMapper
import com.pokemon.ui.theme.PokemonAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var pokemonPresentationToUiMapper: PokemonPresentationToUiMapper

    @Inject
    lateinit var pokemonDetailPresentationToUiMapper: PokemonDetailPresentationToUiMapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokemonAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    App(
                        uiMapper = pokemonPresentationToUiMapper,
                        detailUiMapper = pokemonDetailPresentationToUiMapper
                    )
                }
            }
        }
    }
}
