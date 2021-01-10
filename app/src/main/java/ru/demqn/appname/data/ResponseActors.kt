package ru.demqn.appname.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseActors(
	val cast: List<CastItem>,
	val id: Int
)

@Serializable
data class CastItem(
	@SerialName("cast_id")
	val castId: Int,
	val name: String,
	@SerialName("profile_path")
	val profilePath: String?
)
