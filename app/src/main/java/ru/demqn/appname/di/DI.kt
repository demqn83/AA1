package ru.demqn.appname.di

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import kotlinx.serialization.ExperimentalSerializationApi
import ru.demqn.appname.R
import ru.demqn.appname.data.db.MovieRoomDatabase
import ru.demqn.appname.data.network.MoviesNetwork
import ru.demqn.appname.data.network.RetrofitModule
import ru.demqn.appname.data.repositories.MoviesRepository
import ru.demqn.appname.presentation.MainActivity

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

    private const val CHANNEL = "new_messages"

    fun createNotify(title: String, contentText: String, movieId: Int) {

        val notificationManagerCompat: NotificationManagerCompat =
            NotificationManagerCompat.from(context)

        if (notificationManagerCompat.getNotificationChannel(CHANNEL) == null) {
            notificationManagerCompat.createNotificationChannel(
                NotificationChannelCompat.Builder(
                    CHANNEL,
                    NotificationManagerCompat.IMPORTANCE_HIGH
                )
                    .setName(CHANNEL)
                    .setDescription(CHANNEL)
                    .build()
            )
        }

        val contentUri = "https://android.example.com/movie/${movieId}".toUri()

        val notification = NotificationCompat.Builder(context, CHANNEL)
            .setContentTitle(title)
            .setContentText(contentText)
            .setSmallIcon(R.drawable.ic_like)
//            .setWhen(message.timestamp)
//            .setLargeIcon(bitmapIcon)
            .setContentIntent(
                PendingIntent.getActivity(
                    context,
                    1,
                    Intent(context, MainActivity::class.java)
                        .setAction(Intent.ACTION_VIEW)
                        .setData(contentUri),
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            )
            .build()

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify("chat", movieId, notification)

    }
}