package com.dicoding.movieapi.core.domain.usecase

import com.dicoding.movieapi.core.data.Resource
import com.dicoding.movieapi.core.domain.model.Movies
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovie(): Flow<Resource<List<Movies>>>
    fun getFavoriteMovie():Flow<List<Movies>>
    fun setFavoriteMovie(movies: Movies,state:Boolean)

}