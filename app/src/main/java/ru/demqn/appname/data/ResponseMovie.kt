package ru.demqn.appname.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseMovie(
    val genres: List<GenresItem>,
    val runtime: Int,
    val id: Int,
    val title: String,
    val overview: String,
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("vote_average")
    val voteAverage: Float,
    @SerialName("vote_count")
    val voteCount: Int
)

@Serializable
data class GenresItem(
    val id: Int,
    val name: String
)
