package ru.demqn.appname

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), FragmentMoviesList.TransactionsFragmentClicks, FragmentMoviesDetails.ExitFragmentClicks {

    private var fragmentMoviesList: FragmentMoviesList? = null
    private var fragmentMoviesDetails: FragmentMoviesDetails? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null){
            fragmentMoviesList = FragmentMoviesList()
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

    override fun addMovieDetails(position:Int) {
        val bundle = Bundle()
        bundle.putInt("position", position)
        fragmentMoviesDetails = FragmentMoviesDetails()
        fragmentMoviesDetails!!.arguments = bundle
        fragmentMoviesDetails?.apply {
            supportFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.container_view, this, FRAGMENT_MOVIE_DETAILS_TAG)
                    .commit()
        }
    }

    override fun clickLike(position: Int) {
        var movie: Movie = FakeMovies().getMoviesById(position)
        movie.like = !movie.like
        fragmentMoviesList?.adapterList?.notifyItemChanged(position)
    }

    override fun exitFragment() {
        supportFragmentManager.popBackStack()
    }

    companion object {
        const val FRAGMENT_MOVIE_LIST_TAG = "FragmentMoviesList"
        const val FRAGMENT_MOVIE_DETAILS_TAG = "FragmentMoviesDetails"
    }
}