package ru.demqn.appname.presentation.moviesList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import ru.demqn.appname.data.model.Movie
import ru.demqn.appname.data.repositories.MoviesRepository

class MoviesListViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {
    private var _movieList = moviesRepository.listMoviesRepository
    val movieList: LiveData<List<Movie>> get() = _movieList

    @ExperimentalSerializationApi
    fun getMovies() {
        viewModelScope.launch {
            moviesRepository.getAllMovies()
        }
    }
}