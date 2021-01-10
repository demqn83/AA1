package ru.demqn.appname.data

import kotlinx.serialization.Serializable

@Serializable
data class ResponseMovie(
	val genres: List<GenresItem>,
	val runtime: Int,
	val id: Int
)

@Serializable
data class GenresItem(
	val id: Int,
	val name: String
)
