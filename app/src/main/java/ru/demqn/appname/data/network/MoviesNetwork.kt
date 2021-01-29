package ru.demqn.appname.data.network

import ru.demqn.appname.data.model.*
import ru.demqn.appname.utils.ConfigData

class MoviesNetwork {

    suspend fun nowPlayingData(retrofitMoviesApi: MoviesApi): List<Movie> {

        return retrofitMoviesApi.getNowPlaying().results.map { resultsMovies ->

            retrofitMoviesApi.getMovieById(resultsMovies.id).movieConv(listOf())
        }

    }

    suspend fun moviesById(retrofitMoviesApi: MoviesApi, movieId: Int): Movie {

        return retrofitMoviesApi.getMovieById(movieId).run {
            val actorsCast = retrofitMoviesApi.getPersonId(id).cast
            movieConv(actorsCast)
        }
    }

    private fun ResponseMovie.movieConv(actorsCast: List<CastItem>): Movie {

        return Movie(
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
            actorsCast.map { actor ->
                Actor(
                    actor.castId,
                    actor.name,
                    ConfigData.PATHIMAGE + actor.profilePath
                )
            }
        )
    }
}
