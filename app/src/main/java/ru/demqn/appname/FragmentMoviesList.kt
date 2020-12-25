package ru.demqn.appname

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
import ru.demqn.appname.data.Movie


class FragmentMoviesList : Fragment() {

    private var listener: TransactionsFragmentClicks? = null
    private var movies: List<Movie> = listOf()
    private lateinit var adapterList: MoviesAdapter
    private val movieListViewModel: MoviesListViewModel by viewModels { MoviesListViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)

        val list = view.findViewById<RecyclerView>(R.id.list_movies_recycler_view)
        adapterList = MoviesAdapter(movies, clickListMovies)
        list.adapter = adapterList
        list.layoutManager = GridLayoutManager(requireContext(), 2)

        movieListViewModel.movieList.observe(this.viewLifecycleOwner, this::updtListMovies)
        movieListViewModel.getMovies(requireContext())
        return view
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

    fun updtListMovies(shuffledList: List<Movie>) {
//        val shuffledList = MovieUtil().getMovies(requireContext()).map { it.copy(ratings = (it.ratings / 2)) }
        adapterList.bindMovies(shuffledList)
        val diffCallback = MoviesDiffUtilCallback(movies, shuffledList)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(adapterList)
        movies = shuffledList
    }

    companion object {
        fun newInstance() = FragmentMoviesList()
    }

    interface TransactionsFragmentClicks {
        fun addMovieDetails(movieId: Int)
    }
}