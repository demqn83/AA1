package ru.demqn.appname.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import ru.demqn.appname.R
import ru.demqn.appname.presentation.movieDetails.MoviesDetailsFragment
import ru.demqn.appname.presentation.moviesList.MoviesListFragment

class MainActivity : AppCompatActivity(), MoviesListFragment.TransactionsFragmentClicks,
    MoviesDetailsFragment.ExitFragmentClicks {

    private var moviesListFragment: MoviesListFragment? = null
    private var moviesDetailsFragment: MoviesDetailsFragment? = null

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

    override fun addMovieDetails(movieId: Int) {
        moviesDetailsFragment = MoviesDetailsFragment.newInstance(movieId)
        moviesDetailsFragment?.apply {
            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container_view, this, FRAGMENT_MOVIE_DETAILS_TAG)
                .commit()
        }
    }

    override fun exitFragment() {
        supportFragmentManager.popBackStack()
    }

    private fun handleIntent(intent: Intent) {
        when (intent.action) {
            Intent.ACTION_VIEW -> {
                val moviId = intent.data?.lastPathSegment?.toIntOrNull()
                if (moviId != null) {
                    addMovieDetails(moviId)
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
    }
}