package ru.demqn.appname.data.model

import androidx.room.Entity

@Entity(tableName = "actors")
data class Actor(
    val id: Int,
    val name: String,
    val picture: String
)