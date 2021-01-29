package ru.demqn.appname.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ResponseDates(
	val maximum: String,
	val minimum: String
)