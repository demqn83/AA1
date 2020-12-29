package ru.demqn.appname.data

import android.content.Context

class MovieUtil(private val context: Context) {
    suspend fun getMovies() = loadMovies(context).map { it.copy(ratings = (it.ratings / 2)) }
    suspend fun getMovieById(movieId: Int) = loadMovies(context).find { it.id == movieId }
}