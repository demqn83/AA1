package ru.demqn.appname.di

import android.content.Context
import kotlinx.serialization.ExperimentalSerializationApi
import ru.demqn.appname.data.db.MovieRoomDatabase
import ru.demqn.appname.data.network.MoviesNetwork
import ru.demqn.appname.data.network.RetrofitModule
import ru.demqn.appname.data.repositories.MoviesRepository

object DI {
    private lateinit var context: Context

    fun init(context: Context) {
        this.context = context
    }

    private val database by lazy { MovieRoomDatabase.getDatabase(context) }
    @ExperimentalSerializationApi
    val repository by lazy {
        MoviesRepository(
            database.movieDao(),
            retrofitMoviesApi,
            MoviesNetwork()
        )
    }

    @ExperimentalSerializationApi
    val retrofitMoviesApi by lazy { RetrofitModule.moviesAPI }
}