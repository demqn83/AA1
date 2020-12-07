package ru.demqn.appname

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), FragmentMoviesList.TransactionsFragmentClicks, FragmentMoviesDetails.ExitFragmentClicks {

    private var fragmentMoviesList: FragmentMoviesList? = null
    private var fragmentMoviesDetails: FragmentMoviesDetails? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            fragmentMoviesList = FragmentMoviesList.newInstance()
            fragmentMoviesList?.apply {
                supportFragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .add(R.id.container_view, this, FRAGMENT_MOVIE_LIST_TAG)
                        .commit()
            }
        } else {
            fragmentMoviesList =
                    supportFragmentManager.findFragmentByTag(FRAGMENT_MOVIE_LIST_TAG) as? FragmentMoviesList
        }
    }

    override fun addMovieDetails(id_movie: Int) {
        fragmentMoviesDetails = FragmentMoviesDetails.newInstance(id_movie)
        fragmentMoviesDetails?.apply {
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