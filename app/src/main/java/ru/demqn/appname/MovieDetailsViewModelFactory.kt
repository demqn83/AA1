package ru.demqn.appname

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.demqn.appname.data.network.MoviesNetwork

class MovieDetailsViewModelFactory :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MovieDetailsViewModel::class.java -> MovieDetailsViewModel(MoviesNetwork())
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}