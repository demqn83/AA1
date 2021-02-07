package ru.demqn.appname.data

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import java.util.concurrent.TimeUnit

class MoviesPeriodicWorkRequest {

    private val constraints =
        Constraints.Builder()
//            .setRequiredNetworkType(NetworkType.CONNECTED)
//            .setRequiresCharging(true)
            .build()

    val constrainedRequest =
        PeriodicWorkRequest.Builder(MoviesWorker::class.java, 1, TimeUnit.MINUTES)
//            .setConstraints(constraints)
            .build()
}