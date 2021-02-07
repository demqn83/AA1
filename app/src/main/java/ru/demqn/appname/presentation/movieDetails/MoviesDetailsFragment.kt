package ru.demqn.appname.presentation.movieDetails

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.serialization.ExperimentalSerializationApi
import ru.demqn.appname.ActorsAdapter
import ru.demqn.appname.R
import ru.demqn.appname.data.model.Movie
import ru.demqn.appname.di.DI


class MoviesDetailsFragment : Fragment(R.layout.fragment_movies_details) {

    private var listener: ExitFragmentClicks? = null
    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels {
        MovieDetailsViewModelFactory(DI.repository)
    }
    private lateinit var nameMovie: TextView
    private lateinit var reviews: TextView
    private lateinit var rating: RatingBar
    private lateinit var movieGenre: TextView
    private lateinit var rated: TextView
    private lateinit var description: TextView
    private lateinit var poster: ImageView
    private lateinit var list: RecyclerView


    @ExperimentalSerializationApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.initViews()
        initObserves()
        loadData()
    }

    private fun View.initViews() {
        nameMovie = findViewById(R.id.film_name_text_view)
        reviews = findViewById(R.id.description_rating_text_view)
        rating = findViewById(R.id.rating_bar)
        movieGenre = findViewById(R.id.movie_genre_text_view)
        rated = findViewById(R.id.age_limit_text_view)
        description = findViewById(R.id.story_line_text_view)
        poster = findViewById(R.id.poster_image_view)
        findViewById<ImageView>(R.id.path).setOnClickListener {
            listener?.exitFragment()
        }
        findViewById<TextView>(R.id.caption_back_text_view).setOnClickListener {
            listener?.exitFragment()
        }
        list = findViewById(R.id.actors_recycler_view)
    }

    private fun initObserves() {
        movieDetailsViewModel.movie.observe(this.viewLifecycleOwner, this::updMovie)
    }

    @ExperimentalSerializationApi
    private fun loadData() {
        val movieId = requireNotNull(arguments?.getInt(MOVIE_ID_KEY))
        movieDetailsViewModel.getMovie(movieId)
    }

    private fun updMovie(movie: Movie) {
        nameMovie.text = movie.title
        reviews.text = resources.getString(R.string.reviews, movie.numberOfRatings)
        rating.rating = movie.ratings
        movieGenre.text = movie.genres.joinToString(transform = { it.name })
        rated.text = resources.getString(R.string.age_min, movie.minimumAge)
        description.text = movie.overview
        Glide
            .with(requireContext())
            .load(movie.backdrop)
            .into(poster)

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
        fun newInstance(idMovie: Int) = MoviesDetailsFragment().apply {
            arguments = Bundle().apply {
                putInt(MOVIE_ID_KEY, idMovie)
            }
        }
    }

    interface ExitFragmentClicks {
        fun exitFragment()
    }
}
