package ru.demqn.appname.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Response(
	val dates: Dates,
	val page: Int,
	val results: List<ResultsMovies>
)

@Serializable
data class Dates(
	val maximum: String,
	val minimum: String
)

@Serializable
data class ResultsMovies(
	val overview: String,
	val video: Boolean,
	val title: String,
	@SerialName("poster_path")
	val posterPath: String?,
	@SerialName("backdrop_path")
	val backdropPath: String?,
	val popularity: Double,
	@SerialName("vote_average")
	val voteAverage: Float,
	val id: Int,
	val adult: Boolean,
	@SerialName("vote_count")
	val voteCount: Int
)
