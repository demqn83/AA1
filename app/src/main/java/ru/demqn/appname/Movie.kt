package ru.demqn.appname

data class Movie(
        val id: Int,
        val nameMovie: String,
        val movieDuration: Int,
        val reviews: Int,
        val rating: Double,
        val movieGenre: String,
        val rated: String,
        var like: Boolean,
        val detailPoster: String,
        val poster: String,
        val description: String,
        val listOfActors: List<Actor>
)