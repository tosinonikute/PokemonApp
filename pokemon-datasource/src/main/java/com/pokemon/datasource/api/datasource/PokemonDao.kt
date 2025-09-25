package com.pokemon.datasource.api.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pokemon.datasource.api.model.PokemonInfoDatabaseModel

@Dao
interface PokemonDao {

    @Query("select * from PokemonInfoDatabaseModel")
    fun findAll(): List<PokemonInfoDatabaseModel>

    /**
     * This fetches all reviews Ordered by id from the local database
     */
    @Query("select * from PokemonInfoDatabaseModel where id = :id")
    fun findByRowId(id: Int): List<PokemonInfoDatabaseModel>

    /**
     * This returns total row count of the local database
     */
    @Query("SELECT COUNT(rowid) FROM PokemonInfoDatabaseModel")
    fun getRowCount(): Int

    /**
     * This saves all reviews as a list
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemons(reviewsItem: List<PokemonInfoDatabaseModel>)
}
