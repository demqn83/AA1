package ru.demqn.appname.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.demqn.appname.data.model.Actor
import ru.demqn.appname.data.model.Movie
import ru.demqn.appname.data.model.MovieDB

@Dao
interface MoviesDAO {

    @Transaction
    @Query("SELECT * FROM movies ORDER BY title ASC")
    fun getAllMovies(): LiveData<List<Movie>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movie: MovieDB)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(actor: Actor)

    @Transaction
    @Query("DELETE FROM movies")
    fun deleteALLMovies()

}