package ru.demqn.appname.data.model

import androidx.room.TypeConverter


data class Genre(
    val name: String
)

class GenresConverter {
    @TypeConverter
    fun fromGenres(genres: List<Genre>): String {
        return genres.joinToString()
    }

    @TypeConverter
    fun toGenres(data: String): List<Genre> {
        return data.split(",").map { Genre(it.trim())}
    }
}