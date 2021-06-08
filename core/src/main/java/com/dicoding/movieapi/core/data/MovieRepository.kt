package com.dicoding.movieapi.core.data

import com.dicoding.movieapi.core.data.source.local.LocalDataSource
import com.dicoding.movieapi.core.data.source.remote.RemoteDataSource
import com.dicoding.movieapi.core.data.source.remote.network.ApiResponse
import com.dicoding.movieapi.core.data.source.remote.response.MovieResponse
import com.dicoding.movieapi.core.domain.model.Movies
import com.dicoding.movieapi.core.domain.repository.IMovieRepository
import com.dicoding.movieapi.core.utils.AppExecutors
import com.dicoding.movieapi.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutor: AppExecutors
) : IMovieRepository {

    override fun getAllMovie(): Flow<Resource<List<Movies>>> =
        object : NetworkBoundResource<List<Movies>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movies>> {
                return localDataSource.getAllMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movies>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovie()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movies>> {
        return localDataSource.getFavoriteMovie().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movies: Movies, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movies)
        appExecutor.diskIO().execute{localDataSource.setFavoriteMovie(movieEntity,state)}
    }
}
