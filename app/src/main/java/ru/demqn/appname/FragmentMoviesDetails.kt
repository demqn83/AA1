package ru.demqn.appname

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


class FragmentMoviesDetails : Fragment() {

    private var listener: ExitFragmentClicks? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)
        view.findViewById<ImageView>(R.id.path).setOnClickListener {
            listener?.exitFragment()
        }
        view.findViewById<TextView>(R.id.caption_back_text_view).setOnClickListener {
            listener?.exitFragment()
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ExitFragmentClicks) listener = context
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface ExitFragmentClicks{
        fun exitFragment()
    }
}