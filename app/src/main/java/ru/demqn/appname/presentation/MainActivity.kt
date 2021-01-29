package ru.demqn.appname.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
        } else {
            moviesListFragment =
                supportFragmentManager.findFragmentByTag(FRAGMENT_MOVIE_LIST_TAG) as? MoviesListFragment
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

    companion object {
        const val FRAGMENT_MOVIE_LIST_TAG = "FragmentMoviesList"
        const val FRAGMENT_MOVIE_DETAILS_TAG = "FragmentMoviesDetails"
    }
}