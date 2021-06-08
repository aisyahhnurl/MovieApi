package com.dicoding.movieapi.core.data.source.remote.network

import com.dicoding.movieapi.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getListMovie(
        @Query("api_key") apiKey: String
    ): ListMovieResponse
}