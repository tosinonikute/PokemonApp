package com.pokemon.datasource.api.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pokemon.datasource.api.model.PokemonInfoDatabaseModel

@Database(entities = [PokemonInfoDatabaseModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
