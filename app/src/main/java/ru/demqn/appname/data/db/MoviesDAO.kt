package ru.demqn.appname.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.demqn.appname.data.model.Movie

@Dao
interface MoviesDAO {

    @Transaction
    @Query("SELECT * FROM movies ORDER BY title ASC")
    fun getAllMovies(): LiveData<List<Movie>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movie: Movie)

    @Transaction
    @Query("DELETE FROM movies")
    fun deleteALLMovies()

}