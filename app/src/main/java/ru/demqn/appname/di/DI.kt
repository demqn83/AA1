package ru.demqn.appname.di

import android.content.Context
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import kotlinx.serialization.ExperimentalSerializationApi
import ru.demqn.appname.data.MoviesWorker
import ru.demqn.appname.data.NotificationFactory
import ru.demqn.appname.data.db.MovieRoomDatabase
import ru.demqn.appname.data.network.MoviesNetwork
import ru.demqn.appname.data.network.RetrofitModule
import ru.demqn.appname.data.repositories.MoviesRepository
import java.util.concurrent.TimeUnit

object DI {
    private lateinit var context: Context

    fun init(context: Context) {
        this.context = context

        WorkManager.getInstance(context).enqueue(
            PeriodicWorkRequest.Builder(MoviesWorker::class.java, 10, TimeUnit.MINUTES)
                .build()
        )
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

    val notifications by lazy { NotificationFactory(context) }

}