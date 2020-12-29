package ru.demqn.appname

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.demqn.appname.data.MovieUtil

class MovieDetailsViewModelFactory(private val context: Context) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MovieDetailsViewModel::class.java -> MovieDetailsViewModel(MovieUtil(context))
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}