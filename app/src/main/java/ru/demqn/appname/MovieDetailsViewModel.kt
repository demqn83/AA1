package ru.demqn.appname

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.demqn.appname.data.Movie
import ru.demqn.appname.data.MovieUtil

class MovieDetailsViewModel(private val movieId: Int) : ViewModel() {
    private val _mutableMovie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = _mutableMovie

    fun getMovie(context: Context) {
        viewModelScope.launch {
//            delay(5000)
            _mutableMovie.value = MovieUtil().getMovieById(movieId, context)
        }
    }
}