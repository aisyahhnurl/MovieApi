package com.dicoding.movieapi.core.utils

import com.dicoding.movieapi.core.data.source.local.entity.MovieEntity
import com.dicoding.movieapi.core.data.source.remote.response.MovieResponse
import com.dicoding.movieapi.core.domain.model.Movies

object DataMapper   {
    fun mapResponsesToEntities(input:List<MovieResponse>): List<MovieEntity>{
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = it.title,
                overview = it.overview,
                release_date = it.release_date,
                poster_path = it.poster_path,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }
  fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movies> =
      input.map {
          Movies(
              id = it.id,
              title = it.title,
              overview = it.overview,
              release_date = it.release_date,
              poster_path = it.poster_path,
              isFavorite = it.isFavorite
          )
      }
    fun mapDomainToEntity(input: Movies) = MovieEntity(
        id = input.id,
        title = input.title,
        overview = input.overview,
        release_date = input.release_date,
        poster_path = input.poster_path,
        isFavorite = input.isFavorite
    )
}