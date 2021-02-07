package ru.demqn.appname.presentation

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import ru.demqn.appname.R
import ru.demqn.appname.data.MoviesPeriodicWorkRequest
import ru.demqn.appname.data.MoviesWorker
import ru.demqn.appname.presentation.movieDetails.MoviesDetailsFragment
import ru.demqn.appname.presentation.moviesList.MoviesListFragment
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), MoviesListFragment.TransactionsFragmentClicks,
    MoviesDetailsFragment.ExitFragmentClicks {

    private var moviesListFragment: MoviesListFragment? = null
    private var moviesDetailsFragment: MoviesDetailsFragment? = null
    private val moviesPeriodicWorkRequest = MoviesPeriodicWorkRequest()

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
        } else {
            moviesListFragment =
                supportFragmentManager.findFragmentByTag(FRAGMENT_MOVIE_LIST_TAG) as? MoviesListFragment
        }

        WorkManager.getInstance(this).enqueue(
            PeriodicWorkRequest.Builder(MoviesWorker::class.java, 1, TimeUnit.MINUTES)
            .build())
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

    companion object {
        const val FRAGMENT_MOVIE_LIST_TAG = "FragmentMoviesList"
        const val FRAGMENT_MOVIE_DETAILS_TAG = "FragmentMoviesDetails"
    }
}