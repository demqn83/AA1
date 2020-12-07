package ru.demqn.appname

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class FragmentMoviesList : Fragment() {

    private var listener: TransactionsFragmentClicks? = null
//    private var adapterList: MoviesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)

        val list = view.findViewById<RecyclerView>(R.id.list_movies_recycler_view)
        val movies = FakeMovies().getListMovies()
        val adapterList = MoviesAdapter(movies, listener!!)
        list.adapter = adapterList
        list.layoutManager = GridLayoutManager(requireContext(), 2)

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

    companion object {
        fun newInstance() = FragmentMoviesList()
    }

    interface TransactionsFragmentClicks {
        fun addMovieDetails(movie_id: Int)
    }
}