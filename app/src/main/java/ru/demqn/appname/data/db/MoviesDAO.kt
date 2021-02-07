package ru.demqn.appname.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.demqn.appname.data.model.Movie

@Dao
interface MoviesDAO {

    @Query("SELECT * FROM movies ORDER BY title ASC")
    suspend fun getAllMovies(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: Movie)

    @Query("DELETE FROM movies")
    suspend fun deleteALL()
}