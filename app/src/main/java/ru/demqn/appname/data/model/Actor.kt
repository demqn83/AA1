package ru.demqn.appname.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = "actors",
    foreignKeys = [ForeignKey(
        entity = MovieDB::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("movieId"),
        onDelete = CASCADE
    )
    ],
    primaryKeys = ["id", "movieId"]
)
data class Actor(
    val id: Int,
    val name: String,
    val picture: String,
    val movieId: Int
)