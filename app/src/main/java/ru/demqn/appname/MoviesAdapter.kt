package ru.demqn.appname

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MoviesAdapter(private val movies:List<Movie>, private var listener: ClickListMovies) :
        RecyclerView.Adapter<DataViewHolder>() {

    fun getItem(position: Int): Movie = movies[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false), listener)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    override fun getItemCount(): Int = movies.size
}

class DataViewHolder(itemView: View, private var listener: ClickListMovies) : RecyclerView.ViewHolder(itemView) {
    private val nameMovie: TextView = itemView.findViewById(R.id.nameMovie_text_view)
    private val movieDuration: TextView = itemView.findViewById(R.id.film_duration_text_view)
    private val reviews: TextView = itemView.findViewById(R.id.description_rating_text_view)
    private val rating: RatingBar = itemView.findViewById(R.id.movie_rating_bar)
    private val movieGenre: TextView = itemView.findViewById(R.id.movie_genre_text_view)
    private val rated: TextView = itemView.findViewById(R.id.age_limit_text_view)
    private var like: ImageView = itemView.findViewById(R.id.ic_like_image_view)
    private val poster: ImageView = itemView.findViewById(R.id.poster_image_view)

    fun bind(movie: Movie, position: Int) {
        nameMovie.text = movie.nameMovie
        movieDuration.text = itemView.resources.getString(R.string.min, movie.movieDuration)
        reviews.text = itemView.resources.getString(R.string.reviews, movie.reviews)
        movieGenre.text = movie.movieGenre
        rated.text = movie.rated
        rating.rating = movie.rating.toFloat()

        //как получить размер по вью
        Glide
                .with(itemView.context)
                .load(movie.poster)
//                .override(125, 185)
//            .placeholder(R.drawable.chris_hemsworth)
                .into(poster)

        val likeColor = if (movie.like) R.color.radical_red else R.color.white
        like.setColorFilter(ContextCompat.getColor(itemView.context, likeColor))

        like.setOnClickListener {
            listener.clickLike(position)
        }

        itemView.setOnClickListener {
            listener.clickAddMovieDetails(position)
        }
    }
}

interface ClickListMovies{
    fun clickAddMovieDetails(movieId:Int)
    fun clickLike(movieId:Int)
}
