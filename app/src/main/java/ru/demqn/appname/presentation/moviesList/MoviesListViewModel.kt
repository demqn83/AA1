package ru.demqn.appname.presentation.moviesList

import androidx.lifecycle.ViewModel
import kotlinx.serialization.ExperimentalSerializationApi
import ru.demqn.appname.data.repositories.MoviesRepository

class MoviesListViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    @ExperimentalSerializationApi
    val movieList
        get() = moviesRepository.getAllMovies()

}