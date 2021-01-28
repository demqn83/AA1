package ru.demqn.appname.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.serialization.ExperimentalSerializationApi
import ru.demqn.appname.*
import ru.demqn.appname.data.Movie
import ru.demqn.appname.di.MoviesApplication
import ru.demqn.appname.presentation.presenter.MoviesListViewModel
import ru.demqn.appname.presentation.presenter.MoviesListViewModelFactory


class FragmentMoviesList(moviesApplication: MoviesApplication) : Fragment() {

    private var listener: TransactionsFragmentClicks? = null
    private var movies: List<Movie> = listOf()
    private lateinit var adapterList: MoviesAdapter
    private val movieListViewModel: MoviesListViewModel by viewModels {
        MoviesListViewModelFactory(moviesApplication.repository)
    }
    private lateinit var list: RecyclerView

    @InternalCoroutinesApi
    @ExperimentalSerializationApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        view.initViews()
        initObserves()
        loadData()
        return view
    }

    private fun View.initViews() {
        list = findViewById(R.id.list_movies_recycler_view)
        adapterList = MoviesAdapter(movies, clickListMovies)
        list.adapter = adapterList
        list.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun initObserves() {
        movieListViewModel.movieList.observe(this.viewLifecycleOwner, this::updListMovies)
    }

    @ExperimentalSerializationApi
    @InternalCoroutinesApi
    private fun loadData() {
        movieListViewModel.getMovies()
    }

    private fun updListMovies(shuffledList: List<Movie>) {
        adapterList.bindMovies(shuffledList)
        val diffCallback = MoviesDiffUtilCallback(movies, shuffledList)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(adapterList)
        movies = shuffledList
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
        fun newInstance(moviesApplication: MoviesApplication) = FragmentMoviesList(moviesApplication)
    }

    interface TransactionsFragmentClicks {
        fun addMovieDetails(movieId: Int)
    }
}
