package ru.demqn.appname.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CastItem(
	@SerialName("cast_id")
	val castId: Int,
	val name: String,
	@SerialName("profile_path")
	val profilePath: String?
)