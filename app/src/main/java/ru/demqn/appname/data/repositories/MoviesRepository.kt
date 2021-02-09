package ru.demqn.appname.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.serialization.ExperimentalSerializationApi
import ru.demqn.appname.data.db.MoviesDAO
import ru.demqn.appname.data.model.Movie
import ru.demqn.appname.data.model.MovieWithGenres
import ru.demqn.appname.data.network.MoviesApi
import ru.demqn.appname.data.network.MoviesNetwork

class MoviesRepository(
    private val moviesDAO: MoviesDAO,
    private val retrofitMoviesApi: MoviesApi,
    private val moviesNetwork: MoviesNetwork
) {

    @ExperimentalSerializationApi
    fun getAllMovies(): LiveData<List<MovieWithGenres>> = moviesDAO.getAllMovies()

    suspend fun updateDB() {
        val listMoviesDB = moviesNetwork.nowPlayingData(retrofitMoviesApi)

        moviesDAO.deleteALLMovies()

        listMoviesDB.forEach { movie ->
            moviesDAO.insert(movie)
            movie.genres.forEach { genre ->
                moviesDAO.insert(genre)
            }
        }
        Log.d("TAG", "updateDB")
    }

    suspend fun moviesById(movieId: Int): Movie {
        return moviesNetwork.moviesById(retrofitMoviesApi, movieId)
    }
}