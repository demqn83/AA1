package ru.demqn.appname.presentation

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import ru.demqn.appname.R
import ru.demqn.appname.presentation.movieDetails.MoviesDetailsFragment
import ru.demqn.appname.presentation.moviesList.MoviesListFragment
import java.util.*

class MainActivity : AppCompatActivity(), MoviesListFragment.TransactionsFragmentClicks,
    MoviesDetailsFragment.ExitFragmentClicks, DatePickerFragment.DatePickerFragmentClicks {

    private var moviesListFragment: MoviesListFragment? = null
    private var moviesDetailsFragment: MoviesDetailsFragment? = null
    private var datePickerFragment: DatePickerFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            moviesListFragment = MoviesListFragment.newInstance()
            moviesListFragment?.apply {
                supportFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .add(R.id.container_view, this, FRAGMENT_MOVIE_LIST_TAG)
                    .commit()
            }
            intent?.let(::handleIntent)
        } else {
            moviesListFragment =
                supportFragmentManager.findFragmentByTag(FRAGMENT_MOVIE_LIST_TAG) as? MoviesListFragment
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null) {
            handleIntent(intent)
        }
    }

    override fun addMovieDetails(movieId: Int, view: View?) {
        moviesDetailsFragment = MoviesDetailsFragment.newInstance(movieId)
        moviesDetailsFragment?.apply {
            if (view != null) {
            supportFragmentManager.beginTransaction()
                .addSharedElement(view, "fragmentDetails")
                .addToBackStack(null)
                .replace(R.id.container_view, this, FRAGMENT_MOVIE_DETAILS_TAG)
                .commit()
            }
            else {
                supportFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.container_view, this, FRAGMENT_MOVIE_DETAILS_TAG)
                    .commit()
            }
        }
    }

    override fun exitFragment() {
        supportFragmentManager.popBackStack()
    }

    override fun openDatePickerFragment(nameMovie: String) {
        datePickerFragment = DatePickerFragment.newInstance(nameMovie)
        datePickerFragment?.show(supportFragmentManager, FRAGMENT_DATE_PICKER_TAG)

        CalendarSelectionDialog().show(supportFragmentManager, null)
    }


    override fun processDatePickerResult(
        year: Int,
        month: Int,
        dayOfMonth: Int,
        movieName: String
    ) {
        val calID: Long = 6
        val calendar = Calendar.getInstance()
        val startMillis: Long = calendar.run {
            set(year, month, dayOfMonth, 7, 30)
            timeInMillis
        }
        val endMillis: Long = calendar.run {
            set(year, month, dayOfMonth, 8, 45)
            timeInMillis
        }

        val values = ContentValues().apply {
            put(CalendarContract.Events.DTSTART, startMillis)
            put(CalendarContract.Events.DTEND, endMillis)
            put(CalendarContract.Events.TITLE, getString(R.string.seeMovie))
            put(CalendarContract.Events.DESCRIPTION, movieName)
            put(CalendarContract.Events.CALENDAR_ID, calID)
            put(CalendarContract.Events.EVENT_TIMEZONE, "Europe/Moscow")
        }

        contentResolver.insert(CalendarContract.Events.CONTENT_URI, values)
    }

    private fun handleIntent(intent: Intent) {
        when (intent.action) {
            Intent.ACTION_VIEW -> {
                val moviId = intent.data?.lastPathSegment?.toIntOrNull()
                if (moviId != null) {
                    addMovieDetails(moviId, null)
                    val notificationManagerCompat: NotificationManagerCompat =
                        NotificationManagerCompat.from(applicationContext)

                    notificationManagerCompat.cancel("chat", moviId)
                }
            }
        }
    }

    companion object {
        const val FRAGMENT_MOVIE_LIST_TAG = "FragmentMoviesList"
        const val FRAGMENT_MOVIE_DETAILS_TAG = "FragmentMoviesDetails"
        const val FRAGMENT_DATE_PICKER_TAG = "FragmentDatePicker"
    }
}