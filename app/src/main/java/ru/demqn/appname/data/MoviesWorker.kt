package ru.demqn.appname.data

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import ru.demqn.appname.di.DI

class MoviesWorker(val context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    private val scope = CoroutineScope(Dispatchers.Main)

    @ExperimentalSerializationApi
    override fun doWork(): Result {
        scope.launch {
            DI.repository.updateDB()
            DI.repository.getAllMovies()
        }

        Log.d("TAG", "Worker")
        return Result.success()
    }


}