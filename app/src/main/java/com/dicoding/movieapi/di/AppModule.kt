package com.dicoding.movieapi.di

import com.dicoding.movieapi.core.domain.usecase.MovieInteractor
import com.dicoding.movieapi.core.domain.usecase.MovieUseCase
import com.dicoding.movieapi.detail.DetailViewModel
import com.dicoding.movieapi.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModuleModule = module {
    viewModel { HomeViewModel(get())}
    viewModel { DetailViewModel(get()) }
}