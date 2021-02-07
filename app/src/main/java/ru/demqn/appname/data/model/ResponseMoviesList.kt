package ru.demqn.appname.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ResponseMoviesList(
	val dates: ResponseDates,
	val page: Int,
	val results: List<ResultsMoviesList>
)

