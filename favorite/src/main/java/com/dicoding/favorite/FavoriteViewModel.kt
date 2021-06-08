package com.dicoding.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.movieapi.core.domain.usecase.MovieUseCase


class FavoriteViewModel(movieUseCase: MovieUseCase): ViewModel() {
    val favoriteMovie = movieUseCase.getFavoriteMovie().asLiveData()
}