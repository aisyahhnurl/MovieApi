package com.dicoding.movieapi.detail

import androidx.lifecycle.ViewModel
import com.dicoding.movieapi.core.domain.model.Movies
import com.dicoding.movieapi.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase): ViewModel() {
    fun setFavoriteMovie(movies: Movies,newStatus: Boolean) =
        movieUseCase.setFavoriteMovie(movies,newStatus)
}