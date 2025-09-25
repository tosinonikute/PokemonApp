package com.pokemon.datasource.api.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PokemonInfoDatabaseModel")
data class PokemonInfoDatabaseModel(
    @PrimaryKey val id: Int? = null,

    val name: String? = null,

    val height: Int? = null
)
