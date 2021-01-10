package ru.demqn.appname

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.demqn.appname.data.Movie
import ru.demqn.appname.data.MovieUtil

class MovieDetailsViewModel(private val mMovieUtil: MovieUtil) :
    ViewModel() {
    private val _mutableMovie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _mutableMovie

    fun getMovie(movieId: Int) {
        viewModelScope.launch {
            _mutableMovie.value = mMovieUtil.getMovieByIdAPI(movieId)
//            _mutableMovie.value = getMovie.getMovieById(movieId)
        }
    }
}