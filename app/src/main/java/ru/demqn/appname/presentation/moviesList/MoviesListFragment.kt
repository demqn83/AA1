package ru.demqn.appname.presentation.moviesList

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.serialization.ExperimentalSerializationApi
import ru.demqn.appname.ClickListMovies
import ru.demqn.appname.MoviesAdapter
import ru.demqn.appname.R
import ru.demqn.appname.data.model.Movie
import ru.demqn.appname.data.model.MovieWithGenres
import ru.demqn.appname.di.DI


class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {

    private var listener: TransactionsFragmentClicks? = null
    private var movies: List<Movie> = listOf()
    private lateinit var adapterList: MoviesAdapter

    @ExperimentalSerializationApi
    private val movieListViewModel: MoviesListViewModel by viewModels {
        MoviesListViewModelFactory(DI.repository)
    }
    private lateinit var list: RecyclerView

    @InternalCoroutinesApi
    @ExperimentalSerializationApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.initViews()
        initObserves()
    }

    private fun View.initViews() {
        list = findViewById(R.id.list_movies_recycler_view)
        adapterList = MoviesAdapter(movies, clickListMovies)
        list.adapter = adapterList
        list.layoutManager = GridLayoutManager(requireContext(), 2)
    }


    @ExperimentalSerializationApi
    @InternalCoroutinesApi
    private fun initObserves() {
        movieListViewModel.movieList.observe(viewLifecycleOwner, this::updListMovies)
    }

    private fun updListMovies(shuffledList: List<MovieWithGenres>) {
        val _shuffledList = shuffledList.map { dbMovie ->
            val newMovie = dbMovie.movie.copy(genres = dbMovie.genres)
            newMovie
        }

        adapterList.bindMovies(_shuffledList)
        movies = _shuffledList

        Log.d("TAG", "updListMovies")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is TransactionsFragmentClicks) listener = context
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private val clickListMovies = object : ClickListMovies {
        override fun clickAddMovieDetails(movieId: Int) {
            listener?.addMovieDetails(movieId)
        }

        override fun clickLike(movieId: Int) {
//            movies[movieId] = movies[movieId].copy(like = !movies[movieId].like)
//            adapterList?.notifyItemChanged(movieId)
        }
    }

    companion object {
        fun newInstance() =
            MoviesListFragment()
    }

    interface TransactionsFragmentClicks {
        fun addMovieDetails(movieId: Int)
    }
}