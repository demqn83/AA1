package ru.demqn.appname

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MoviesAdapter(context: Context, private var listener: FragmentMoviesList.TransactionsFragmentClicks):
    RecyclerView.Adapter<DataViewHolder>() {

    private val inflater : LayoutInflater = LayoutInflater.from(context)

    fun getItem(position: Int) : Movie = FakeMovies().getMoviesById(position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(inflater.inflate(R.layout.view_holder_movie, parent, false), listener)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.Bind(getItem(position), position)
    }

    override fun getItemCount(): Int = FakeMovies().getListMovies().size
}

class DataViewHolder(itemView: View, private var listener: FragmentMoviesList.TransactionsFragmentClicks) : RecyclerView.ViewHolder(itemView){
    private val nameMovie: TextView? = itemView.findViewById(R.id.nameMovie_text_view)
    private val movieDuration: TextView? = itemView.findViewById(R.id.film_duration_text_view)
    private val reviews: TextView? = itemView.findViewById(R.id.description_rating_text_view)
    private val rating: RatingBar? = itemView.findViewById(R.id.movie_rating_bar)
    private val movieGenre: TextView? = itemView.findViewById(R.id.movie_genre_text_view)
    private val rated: TextView? = itemView.findViewById(R.id.age_limit_text_view)
    private var like: ImageView? = itemView.findViewById(R.id.ic_like_image_view)
    private val poster: ImageView? = itemView.findViewById(R.id.poster_image_view)

    fun Bind(movie: Movie, position:Int) {
        nameMovie?.text = movie.nameMovie
        movieDuration?.text = "${movie.movieDuration} min" //какой способ лучше использовать ?
        reviews?.text = movie.reviews.toString() + " reviews"
        movieGenre?.text = movie.movieGenre
        rated?.text = movie.rated
        rating?.rating = movie.rating.toFloat()

        //как получить размер по вью
        Glide
            .with(itemView.context)
            .load(movie.poster)
            .override(125, 185)
//            .placeholder(R.drawable.chris_hemsworth)
            .into(poster!!);

        if (movie.like) {
            like?.setColorFilter(ContextCompat.getColor(itemView.context, R.color.radical_red))
        }
        else {
            like?.setColorFilter(ContextCompat.getColor(itemView.context, R.color.white))
        }

        like?.setOnClickListener {
            if (movie.like) {
                movie.like = false
                like?.setColorFilter(ContextCompat.getColor(itemView.context, R.color.white))
            } else {
                movie.like = true
                like?.setColorFilter(ContextCompat.getColor(itemView.context, R.color.radical_red))
            }
        }

        itemView.findViewById<ImageView>(R.id.ic_bg_image_view).setOnClickListener {
            listener?.addMovieDetails(position)
        }
    }
}
