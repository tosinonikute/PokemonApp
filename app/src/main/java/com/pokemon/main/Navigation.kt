package com.pokemon.main

import androidx.annotation.MainThread
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

inline fun <reified T : @Serializable Any> NavGraphBuilder.composableNoAnimation(
    deepLinks: List<NavDeepLink> = emptyList(),
    noinline content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) = composable<T>(
    deepLinks = deepLinks,
    enterTransition = { null },
    exitTransition = { null },
    popEnterTransition = { null },
    popExitTransition = { null },
    content = content
)

@MainThread
internal fun <T : Any> NavHostController.navigateSingleTop(
    route: T,
) = navigate(route = route) {
    launchSingleTop = true
}
