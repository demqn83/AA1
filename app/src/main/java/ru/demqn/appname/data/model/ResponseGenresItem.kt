package ru.demqn.appname.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ResponseGenresItem(
    val id: Int,
    val name: String
)