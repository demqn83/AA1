package ru.demqn.appname.data.network

import ru.demqn.appname.ConfigData
import ru.demqn.appname.data.Actor
import ru.demqn.appname.data.Genre
import ru.demqn.appname.data.Movie

class MoviesNetwork {

    suspend fun nowPlayingData(retrofitMoviesApi: MoviesApi): List<Movie> {

        return retrofitMoviesApi.getNowPlaying().results.map {

            val movieExtra = retrofitMoviesApi.getMovieById(it.id)

            Movie(
                it.id,
                it.title,
                it.overview,
                ConfigData.PATHIMAGE + it.posterPath,
                ConfigData.PATHIMAGE + it.backdropPath,
                it.voteAverage / 2,
                it.voteCount,
                if (it.adult) 16 else 13,
                movieExtra.runtime,
                movieExtra.genres.map { genr -> Genre(genr.id, genr.name) },
                listOf()
            )
        }

    }

    suspend fun moviesById(retrofitMoviesApi: MoviesApi, movieId: Int): Movie {

        return retrofitMoviesApi.getMovieById(movieId).run {

            val actors = retrofitMoviesApi.getPersonId(id)

            Movie(
                id,
                title,
                overview,
                ConfigData.PATHIMAGE + posterPath,
                ConfigData.PATHIMAGE + backdropPath,
                voteAverage / 2,
                voteCount,
                if (adult) 16 else 13,
                runtime,
                genres.map { genr -> Genre(genr.id, genr.name) },
                actors.cast.map { actor ->
                    Actor(actor.castId, actor.name, ConfigData.PATHIMAGE + actor.profilePath ?: "")
                }
            )
        }

    }
}