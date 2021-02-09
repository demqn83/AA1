package ru.demqn.appname.data.model

import androidx.room.*


@Entity(tableName = "movies")
data class Movie @JvmOverloads constructor(
    @PrimaryKey
    val id: Int,
    val title: String,
    val overview: String,
    val poster: String,
    val backdrop: String,
    val ratings: Float,
    val numberOfRatings: Int,
    val minimumAge: Int,
    val runtime: Int,
    @Ignore
    val genres: List<Genre> = listOf(),
    @Ignore
    val actors: List<Actor> = listOf()
)

data class MovieWithGenres(
    @Embedded
    val movie: Movie,

    @Relation(parentColumn = "id", entity = Genre::class, entityColumn = "movieId")
    val genres: List<Genre>
)
