package com.pokemon.presentation.state


data class PaginationState(
    val currentPage: Int = 1,
    val totalPages: Int = 1,
    val isLoading: Boolean = false,
    val canLoadMore: Boolean = false
)
