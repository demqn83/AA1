package ru.demqn.appname.data

import android.content.Context

class MovieUtil {
    suspend fun getMovies(context: Context) = loadMovies(context)
    suspend fun getMovieById(movieId: Int, context: Context) =
        loadMovies(context).find { it.id == movieId }
}