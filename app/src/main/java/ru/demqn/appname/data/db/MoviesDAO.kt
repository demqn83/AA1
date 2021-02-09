package ru.demqn.appname.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.demqn.appname.data.model.Genre
import ru.demqn.appname.data.model.Movie
import ru.demqn.appname.data.model.MovieWithGenres

@Dao
interface MoviesDAO {

    @Transaction
    @Query("SELECT * FROM movies ORDER BY title ASC")
    fun getAllMovies(): LiveData<List<MovieWithGenres>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movie: Movie)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(genre: Genre)

    @Transaction
    @Query("DELETE FROM movies")
    fun deleteALLMovies()

}