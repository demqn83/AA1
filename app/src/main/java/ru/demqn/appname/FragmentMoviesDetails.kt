package ru.demqn.appname

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.demqn.appname.data.Movie


class FragmentMoviesDetails : Fragment() {

    private var listener: ExitFragmentClicks? = null
    private lateinit var movieDetailsViewModel: MovieDetailsViewModel ///???

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val movieIdKey = requireNotNull(arguments?.getInt(MOVIE_ID_KEY))
        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)
//        movieDetailsViewModel = MovieDetailsViewModel(movieIdKey)
        movieDetailsViewModel = ViewModelProvider(this, MovieDetailsViewModelFactory(movieIdKey)).get(MovieDetailsViewModel::class.java)

        movieDetailsViewModel.movie.observe(this.viewLifecycleOwner, this::updMovie)
        movieDetailsViewModel.getMovie(requireContext())

        view.findViewById<ImageView>(R.id.path).setOnClickListener {
            listener?.exitFragment()
        }
        view.findViewById<TextView>(R.id.caption_back_text_view).setOnClickListener {
            listener?.exitFragment()
        }

        return view
    }

    fun updMovie(movie: Movie) {
        val view = requireNotNull(view)

        val nameMovie: TextView = view.findViewById<TextView>(R.id.film_name_text_view)
        val reviews: TextView = view.findViewById(R.id.description_rating_text_view)
        val rating: RatingBar = view.findViewById(R.id.rating_bar)
        val movieGenre: TextView = view.findViewById(R.id.movie_genre_text_view)
        val rated: TextView = view.findViewById(R.id.age_limit_text_view)
        val description: TextView = view.findViewById(R.id.story_line_text_view)
        val poster: ImageView = view.findViewById(R.id.poster_image_view)

        nameMovie.text = movie.title
        reviews.text = resources.getString(R.string.reviews, movie.numberOfRatings)
        rating.rating = movie.ratings
        movieGenre.text = movie.genres.joinToString(transform = { it -> it.name })
        rated.text = resources.getString(R.string.age_min, movie.minimumAge)
        description.text = movie.overview
        Glide
            .with(view.context)
            .load(movie.backdrop)
            .into(poster)

        val list = view.findViewById<RecyclerView>(R.id.actors_recycler_view)
        val actors = movie.actors
        val adapter = ActorsAdapter(actors)
        list.adapter = adapter
        list.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ExitFragmentClicks) listener = context
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        private const val MOVIE_ID_KEY = "id_movie"
        fun newInstance(idMovie: Int) = FragmentMoviesDetails().apply {
            arguments = Bundle().apply {
                putInt(MOVIE_ID_KEY, idMovie)
            }
        }
    }

    interface ExitFragmentClicks {
        fun exitFragment()
    }
}