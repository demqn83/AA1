package ru.demqn.appname.di

import android.app.Application
import kotlinx.serialization.ExperimentalSerializationApi
import ru.demqn.appname.data.db.MovieRoomDatabase
import ru.demqn.appname.data.repositories.MoviesRepository
import ru.demqn.appname.data.network.RetrofitModule

class MoviesApplication : Application() {

    private val database by lazy { MovieRoomDatabase.getDatabase(this) }
    val repository by lazy { MoviesRepository(database.movieDao()) }

    @ExperimentalSerializationApi
    val retrofitMoviesApi by lazy { RetrofitModule.moviesAPI }
}