package ru.demqn.appname.data

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import ru.demqn.appname.R
import ru.demqn.appname.presentation.MainActivity

class NotificationFactory(private val context: Context) {

    private val CHANNEL = "new_messages"

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