package ru.demqn.appname

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment


class FragmentMoviesList : Fragment() {

    private var listener: TransactionsFragmentClicks? = null
    private var colorLike:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        view.findViewById<ImageView>(R.id.ic_bg_image_view).setOnClickListener {
            listener?.addMovieDetails()
        }

        var likeImageView = view.findViewById<ImageView>(R.id.ic_like_image_view);
        likeImageView.setOnClickListener {
            if (colorLike) {
                colorLike = false
                DrawableCompat.setTint(likeImageView.getDrawable(), ContextCompat.getColor(container!!.context, R.color.white));
            } else {
                colorLike = true
                DrawableCompat.setTint(likeImageView.getDrawable(), ContextCompat.getColor(container!!.context, R.color.radical_red));
            }

//            DrawableCompat.setTint(likeImageView.getDrawable(), ContextCompat.getColor(container!!.context, R.color.radical_red));
//            val toast = Toast.makeText(context, colorLike.toString(), Toast.LENGTH_LONG)
//            toast.show()
        }

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

    interface TransactionsFragmentClicks {
        fun addMovieDetails()
    }
}