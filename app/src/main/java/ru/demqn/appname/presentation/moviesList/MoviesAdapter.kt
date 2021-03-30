package ru.demqn.appname

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.demqn.appname.data.model.Movie
import ru.demqn.appname.presentation.moviesList.MoviesDiffUtilCallback

class MoviesAdapter(private var movies: List<Movie>, private var listener: ClickListMovies) :
    RecyclerView.Adapter<DataViewHolder>() {

    private fun getItem(position: Int): Movie = movies[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun getItemCount(): Int = movies.size

    fun bindMovies(newMovies: List<Movie>) {
        val diffCallback = MoviesDiffUtilCallback(movies, newMovies)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)
        movies = newMovies
    }
}

class DataViewHolder(itemView: View, private var listener: ClickListMovies) :
    RecyclerView.ViewHolder(itemView) {
    private val nameMovie: TextView = itemView.findViewById(R.id.nameMovie_text_view)
    private val movieDuration: TextView = itemView.findViewById(R.id.film_duration_text_view)
    private val reviews: TextView = itemView.findViewById(R.id.description_rating_text_view)
    private val rating: RatingBar = itemView.findViewById(R.id.movie_rating_bar)
    private val movieGenre: TextView = itemView.findViewById(R.id.movie_genre_text_view)
    private val rated: TextView = itemView.findViewById(R.id.age_limit_text_view)

    //    private var like: ImageView = itemView.findViewById(R.id.ic_like_image_view)
    private val poster: ImageView = itemView.findViewById(R.id.poster_image_view)

    fun onBind(movie: Movie) {
        itemView.transitionName = movie.title + movie.id
        nameMovie.text = movie.title
        movieDuration.text = itemView.resources.getString(R.string.min, movie.runtime)
        reviews.text = itemView.resources.getString(R.string.reviews, movie.numberOfRatings)
        movieGenre.text = movie.genres.joinToString(transform = { it.name })
        rated.text = itemView.resources.getString(R.string.age_min, movie.minimumAge)
        rating.rating = movie.ratings

        Glide
            .with(itemView.context)
            .load(movie.poster)
            .into(poster)

        itemView.setOnClickListener {
            listener.clickAddMovieDetails(movie.id, itemView)
        }
    }
}

interface ClickListMovies {
    fun clickAddMovieDetails(movieId: Int, view: View)
    fun clickLike(movieId: Int)
}
