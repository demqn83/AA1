package ru.demqn.appname

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.demqn.appname.data.Movie
import ru.demqn.appname.data.MovieUtil

class MoviesListViewModel(private val mMovieUtil: MovieUtil) : ViewModel() {
    private val _mutableMovieList = MutableLiveData<List<Movie>>(emptyList())
    val movieList: LiveData<List<Movie>> get() = _mutableMovieList

    fun getMovies() {
        viewModelScope.launch {
            _mutableMovieList.value = mMovieUtil.getMoviesAPI()
//            mMovieUtil.getMoviesAPI()
        }
    }
}