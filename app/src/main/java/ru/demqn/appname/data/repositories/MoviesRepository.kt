package ru.demqn.appname.data.repositories

import androidx.lifecycle.LiveData
import kotlinx.serialization.ExperimentalSerializationApi
import ru.demqn.appname.data.db.MoviesDAO
import ru.demqn.appname.data.model.Movie
import ru.demqn.appname.data.model.MovieDB
import ru.demqn.appname.data.network.MoviesApi
import ru.demqn.appname.data.network.MoviesNetwork
import ru.demqn.appname.di.DI

class MoviesRepository(
    private val moviesDAO: MoviesDAO,
    private val retrofitMoviesApi: MoviesApi,
    private val moviesNetwork: MoviesNetwork
) {

    @ExperimentalSerializationApi
    fun getAllMovies(): LiveData<List<Movie>> = moviesDAO.getAllMovies()

    suspend fun updateDB() {
        val listMoviesDB = moviesNetwork.nowPlayingData(retrofitMoviesApi)

        moviesDAO.deleteALLMovies()

        listMoviesDB.forEach { movie ->
            moviesDAO.insert(
                MovieDB(
                    movie.id,
                    movie.title,
                    movie.overview,
                    movie.poster,
                    movie.backdrop,
                    movie.ratings,
                    movie.numberOfRatings,
                    movie.minimumAge,
                    movie.runtime,
                    movie.genres
                )
            )
        }

        DI.notifications.createNotify(
            "Movie",
            "Фильм с самым высоким рейтингом",
            moviesDAO.getMovieMaxRating()
        )
    }

    suspend fun moviesById(movieId: Int): Movie {
        val movie = moviesNetwork.moviesById(retrofitMoviesApi, movieId)
        movie.actors.forEach { actor ->
            moviesDAO.insert(actor)
        }
        return movie
    }
}