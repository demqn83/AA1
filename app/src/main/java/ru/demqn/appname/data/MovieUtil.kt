package ru.demqn.appname.data

import android.content.Context
import ru.demqn.appname.LoadMoviesAPI

class MovieUtil(private val context: Context) {

    suspend fun getMoviesAPI() = LoadMoviesAPI().nowPlayingData()

    suspend fun getMovieByIdAPI(movieId: Int) = LoadMoviesAPI().moviesById(movieId)

}
