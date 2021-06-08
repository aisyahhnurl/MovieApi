package com.dicoding.movieapi.core.data.source.local.room

import androidx.room.*
import com.dicoding.movieapi.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getAllMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies where isFavorite = 1")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(movies: MovieEntity)
}