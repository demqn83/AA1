package ru.demqn.appname.di

import android.app.Application
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import ru.demqn.appname.data.MoviesWorker
import java.util.concurrent.TimeUnit

class MoviesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DI.init(this)

        WorkManager.getInstance(this).enqueue(
            PeriodicWorkRequest.Builder(MoviesWorker::class.java, 1, TimeUnit.MINUTES)
                .build()
        )
    }
}