package ru.demqn.appname.data.model

import androidx.room.*


@Entity(tableName = "movies")
@TypeConverters(GenresConverter::class)
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
    val genres: List<Genre>,
    @Ignore
    val actors: List<Actor> = listOf()
)
