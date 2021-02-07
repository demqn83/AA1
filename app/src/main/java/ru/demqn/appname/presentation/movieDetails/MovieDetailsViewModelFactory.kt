package ru.demqn.appname.presentation.movieDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.demqn.appname.data.network.MoviesNetwork
import ru.demqn.appname.data.repositories.MoviesRepository
import ru.demqn.appname.di.DI.repository

class MovieDetailsViewModelFactory(repository: MoviesRepository) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MovieDetailsViewModel::class.java -> MovieDetailsViewModel(repository)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}