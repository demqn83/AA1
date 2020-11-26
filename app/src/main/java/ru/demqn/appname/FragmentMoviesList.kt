package ru.demqn.appname

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment


class FragmentMoviesList : Fragment() {

    private var imageview_poster: ImageView? = null
    private var listener: TransactionsFragmentClicks? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        imageview_poster = view.findViewById<ImageView>(R.id.poster_avenger_image_view).apply {
//            setOnClickListener {
//                listener?.addMovieDetails()
//            }
//        }
        view.setOnClickListener {
            listener?.addMovieDetails()
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is TransactionsFragmentClicks) listener = context
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun setClickListener(l: TransactionsFragmentClicks?) {
        listener = l
    }

    interface TransactionsFragmentClicks {
        fun addMovieDetails()
    }
}