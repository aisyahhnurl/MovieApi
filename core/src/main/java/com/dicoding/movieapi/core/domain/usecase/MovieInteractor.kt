package com.dicoding.movieapi.core.domain.usecase

import com.dicoding.movieapi.core.domain.model.Movies
import com.dicoding.movieapi.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {
    override fun getAllMovie() = movieRepository.getAllMovie()
    override fun getFavoriteMovie() = movieRepository.getFavoriteMovie()
    override fun setFavoriteMovie(movies: Movies, state: Boolean) = movieRepository.setFavoriteMovie(movies,state)


}