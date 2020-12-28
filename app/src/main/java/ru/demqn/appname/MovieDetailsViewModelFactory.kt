package ru.demqn.appname

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.demqn.appname.data.MovieUtil

class MovieDetailsViewModelFactory(private val context: Context, private val movieId: Int) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MovieDetailsViewModel::class.java -> MovieDetailsViewModel(MovieUtil(context), movieId)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}