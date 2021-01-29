package ru.demqn.appname.presentation.movieDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import ru.demqn.appname.data.model.Movie
import ru.demqn.appname.data.network.MoviesNetwork
import ru.demqn.appname.data.repositories.MoviesRepository
import ru.demqn.appname.di.MoviesApplication

class MovieDetailsViewModel(private val repository: MoviesRepository) : ViewModel() {
    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _movie

    @ExperimentalSerializationApi
    fun getMovie(movieId: Int) {
        viewModelScope.launch {
            _movie.value = repository.moviesById(movieId)
        }
    }
}