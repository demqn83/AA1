package ru.demqn.appname

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.demqn.appname.data.Movie
import ru.demqn.appname.data.loadMovies


class FragmentMoviesList : Fragment() {

    private var listener: TransactionsFragmentClicks? = null
//    private val movies = FakeMovies().getListMovies().toMutableList()
    private val movies: List<Movie> = listOf()
    private var adapterList: MoviesAdapter? = null
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)

        val list = view.findViewById<RecyclerView>(R.id.list_movies_recycler_view)
        adapterList = MoviesAdapter(movies, clickListMovies)
        list.adapter = adapterList
        list.layoutManager = GridLayoutManager(requireContext(), 2)

        scope.launch { updtListMovies() }
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

    suspend fun updtListMovies() = withContext(Dispatchers.Main) {
//        Toast.makeText(context, "Начало", Toast.LENGTH_SHORT).show()
        var shuffledList = loadMovies(requireContext()).filter { it.ratings <= 5 }
        adapterList!!.bindMovies(shuffledList!!)
        val diffCallback = MoviesDiffUtilCallback(movies, shuffledList)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(adapterList!!)
//        Toast.makeText(context, "Конец", Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = FragmentMoviesList()
    }

    interface TransactionsFragmentClicks {
        fun addMovieDetails(movie_id: Int)
    }
}