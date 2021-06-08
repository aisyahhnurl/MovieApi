package com.dicoding.movieapi.core.data.source.local


import com.dicoding.movieapi.core.data.source.local.entity.MovieEntity
import com.dicoding.movieapi.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {

    fun getAllMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovie()

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovie()

    suspend fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

    fun setFavoriteMovie(movies: MovieEntity, newState: Boolean) {
        movies.isFavorite = newState
        movieDao.updateFavoriteMovie(movies)
    }
    }
