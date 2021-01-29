package ru.demqn.appname.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.serialization.ExperimentalSerializationApi
import ru.demqn.appname.data.db.MoviesDAO
import ru.demqn.appname.data.model.Movie
import ru.demqn.appname.data.network.MoviesApi
import ru.demqn.appname.data.network.MoviesNetwork
import kotlin.random.Random

class MoviesRepository(
    private val moviesDAO: MoviesDAO,
    private val retrofitMoviesApi: MoviesApi,
    private val moviesNetwork: MoviesNetwork
) {

    private var _listMoviesRepository = MutableLiveData<List<Movie>>(emptyList())
    val listMoviesRepository: LiveData<List<Movie>> get() = _listMoviesRepository

    @ExperimentalSerializationApi
    suspend fun getAllMovies() {

        _listMoviesRepository.value = moviesDAO.getAllMovies()

        val listMoviesDB = moviesNetwork.nowPlayingData(retrofitMoviesApi)
        _listMoviesRepository.value = listMoviesDB

        moviesDAO.deleteALL()
        moviesDAO.insert(listMoviesDB[Random.nextInt(listMoviesDB.size - 1)])
        moviesDAO.insert(listMoviesDB[Random.nextInt(listMoviesDB.size - 1)])
    }

    suspend fun moviesById(movieId: Int): Movie {
        return moviesNetwork.moviesById(retrofitMoviesApi, movieId)
    }
}