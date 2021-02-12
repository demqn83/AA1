package ru.demqn.appname.data.model

import androidx.room.*


data class Movie (
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
    val genres: List<Genre>,
    @Relation(
        parentColumn = "id",
        entityColumn = "movieId"
    )
    val actors: List<Actor>
)

@Entity(tableName = "movies")
data class MovieDB (
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
    val genres: List<Genre> = listOf()
)