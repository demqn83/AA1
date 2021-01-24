package ru.demqn.appname.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

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