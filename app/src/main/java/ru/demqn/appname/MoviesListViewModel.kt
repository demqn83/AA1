package ru.demqn.appname

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.demqn.appname.data.Movie
import ru.demqn.appname.data.MovieUtil

class MoviesListViewModel : ViewModel() {

    private val _mutableMovieList = MutableLiveData<List<Movie>>(emptyList())
    val movieList: LiveData<List<Movie>> get() = _mutableMovieList

    fun getMovies(context: Context) {
        viewModelScope.launch {
//            delay(5000)
            _mutableMovieList.value =
                MovieUtil().getMovies(context).map { it.copy(ratings = (it.ratings / 2)) }
        }
    }
}