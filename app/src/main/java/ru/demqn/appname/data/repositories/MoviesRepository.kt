package ru.demqn.appname.data.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.demqn.appname.data.db.MoviesDAO
import ru.demqn.appname.data.Movie

class MoviesRepository(private val moviesDAO: MoviesDAO) {

    suspend fun getAllMovies() = withContext(Dispatchers.IO) {
        moviesDAO.getAllMovies()
    }

    suspend fun insert(movie: Movie) {
        moviesDAO.insert(movie)
    }

    suspend fun deleteAll() {
        moviesDAO.deleteALL()
    }
}