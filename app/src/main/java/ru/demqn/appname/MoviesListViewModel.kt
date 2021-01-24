package ru.demqn.appname

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import ru.demqn.appname.data.Movie
import ru.demqn.appname.data.network.MoviesNetwork
import ru.demqn.appname.data.repositories.MoviesRepository
import ru.demqn.appname.di.MoviesApplication

class MoviesListViewModel(
    private val moviesNetwork: MoviesNetwork,
    private val moviesRepository: MoviesRepository
) : ViewModel() {
    private val _movieList = MutableLiveData<List<Movie>>(emptyList())
    val movieList: LiveData<List<Movie>> get() = _movieList

    @ExperimentalSerializationApi
    fun getMovies() {
        viewModelScope.launch {
            var listMovies: List<Movie> = moviesRepository.getAllMovies()
            _movieList.value = listMovies
            listMovies = moviesNetwork.nowPlayingData(MoviesApplication().retrofitMoviesApi)
            moviesRepository.deleteAll()
            moviesRepository.insert(listMovies[0])
            moviesRepository.insert(listMovies[1])
            _movieList.value = listMovies
        }
    }
}