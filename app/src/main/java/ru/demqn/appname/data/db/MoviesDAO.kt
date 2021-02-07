package ru.demqn.appname.data.db

import androidx.room.*
import ru.demqn.appname.data.model.Genre
import ru.demqn.appname.data.model.Movie
import ru.demqn.appname.data.model.MovieWithGenres

@Dao
interface MoviesDAO {

    @Transaction
    @Query("SELECT * FROM movies ORDER BY title ASC")
    suspend fun getAllMovies(): List<MovieWithGenres>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(genre: Genre)

    @Query("DELETE FROM movies")
    suspend fun deleteALL()
}