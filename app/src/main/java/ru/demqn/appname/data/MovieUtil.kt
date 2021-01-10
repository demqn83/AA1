package ru.demqn.appname.data

import android.content.Context
import ru.demqn.appname.LoadMoviesAPI

class MovieUtil(private val context: Context) {

    suspend fun getMoviesAPI() = LoadMoviesAPI().nowPlayingData()

    suspend fun getMovieByIdAPI(movieId: Int) =
        LoadMoviesAPI().nowPlayingData().find { it.id == movieId }


//    suspend fun getMovies() = loadMovies(context).map { it.copy(ratings = (it.ratings / 2)) }
//    suspend fun getMovieById(movieId: Int) = loadMovies(context).find { it.id == movieId }
}
