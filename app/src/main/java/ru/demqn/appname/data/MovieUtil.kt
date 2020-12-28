package ru.demqn.appname.data

import android.content.Context

class MovieUtil(private val context: Context) {
    suspend fun getMovies() = loadMovies(context)
    suspend fun getMovieById(movieId: Int) = loadMovies(context).find { it.id == movieId }
}