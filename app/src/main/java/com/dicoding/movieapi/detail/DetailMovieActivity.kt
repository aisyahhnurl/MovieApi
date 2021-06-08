package com.dicoding.movieapi.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.dicoding.movieapi.R
import com.dicoding.movieapi.core.domain.model.Movies
import com.dicoding.movieapi.core.utils.PosterConstants
import com.dicoding.movieapi.databinding.ActivityDetailMovieBinding
import kotlinx.android.synthetic.main.activity_detail_movie.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailMovie = intent.getParcelableExtra<Movies>(EXTRA_DATA)
        if (detailMovie != null) {
            showDetailMovie(detailMovie)
        }
    }

    private fun showDetailMovie(detailMovie: Movies) {
        with(binding){
            titleMovie.text = detailMovie.title
            movie_release.text = detailMovie.release_date
            description.text = detailMovie.overview
            Glide.with(this@DetailMovieActivity)
                .load("${PosterConstants.POSTER_URL}${detailMovie.poster_path}")
                .into(binding.imgMovie)

            var statusFavorite = detailMovie.isFavorite
            setStatusFavorite(statusFavorite)
            binding.btnFavButton.setOnClickListener{
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteMovie(detailMovie,statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean){
        if (statusFavorite) {
            binding.btnFavButton.setImageDrawable(
                ContextCompat.getDrawable(
                    this.applicationContext,
                    R.drawable.ic_favorite_black
                )
            )
        } else {
            binding.btnFavButton.setImageDrawable(
                ContextCompat.getDrawable(
                    this.applicationContext,
                    R.drawable.ic_favorite_white
                )
            )
        }
    }
    }
