package com.pokemon.main

import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes {
    @Serializable
    data object Home : Routes

    @Serializable
    data object List : Routes

    @Serializable
    data object Detail : Routes
}
