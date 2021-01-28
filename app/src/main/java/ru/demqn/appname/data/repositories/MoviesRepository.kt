package ru.demqn.appname.data.repositories

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import ru.demqn.appname.data.Movie
import ru.demqn.appname.data.db.MoviesDAO
import ru.demqn.appname.data.network.MoviesNetwork
import ru.demqn.appname.di.MoviesApplication
import kotlin.random.Random

class MoviesRepository(private val moviesDAO: MoviesDAO) {

    val listMoviesRepository = MutableLiveData<List<Movie>>(emptyList())

    @ExperimentalSerializationApi
    suspend fun getAllMovies() {

        var listMoviesDB: List<Movie>
        withContext(Dispatchers.IO) { listMoviesDB = moviesDAO.getAllMovies() }
        listMoviesRepository.value = listMoviesDB

        listMoviesDB = MoviesNetwork().nowPlayingData(MoviesApplication().retrofitMoviesApi)
        listMoviesRepository.value = listMoviesDB

        moviesDAO.deleteALL()
        moviesDAO.insert(listMoviesDB[Random.nextInt (listMoviesDB.size - 1)])
        moviesDAO.insert(listMoviesDB[Random.nextInt(listMoviesDB.size-1)])

    }
}