package ru.demqn.appname.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ResponseActors(
	val cast: List<CastItem>,
	val id: Int
)
