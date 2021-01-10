package ru.demqn.appname

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import ru.demqn.appname.data.*

class LoadMoviesAPI {

    suspend fun nowPlayingData(): List<Movie> {
        //получить путь - что значят значения -- позже
        // получить данные из /movie/now_playing
        return RetrofitModule.moviesAPI.getNowPlaying().results.map {

            //дозаполнить данные из /movie/{movie_id} так как нет данных по runtime для списка
            val movieExtra = RetrofitModule.moviesAPI.getMovieById(it.id)
            val actors = RetrofitModule.moviesAPI.getPersonId(it.id)

            Movie(
                it.id,
                it.title,
                it.overview,
                PATHIMAGE + it.posterPath,
                PATHIMAGE + it.backdropPath,
                it.voteAverage / 2,
                it.voteCount,
                if (it.adult) 16 else 13,
                movieExtra.runtime,
                movieExtra.genres.map { genr -> Genre(genr.id, genr.name) },
                actors.cast.map { actor ->
                    Actor(actor.castId, actor.name, PATHIMAGE + actor.profilePath ?: "")
                }
            )
        }

    }

    private interface MoviesApi {
        @GET("3/movie/now_playing?api_key=$APIKEY&language=$LANGUAGE&page=1")
        suspend fun getNowPlaying(): Response

        @GET("3/movie/{movie_id}?api_key=$APIKEY&language=$LANGUAGE")
        suspend fun getMovieById(@Path("movie_id") movieId: Int): ResponseMovie

        @GET("3/movie/{movie_id}/credits?api_key=$APIKEY&language=$LANGUAGE")
        suspend fun getPersonId(@Path("movie_id") movieId: Int): ResponseActors
    }

    private object RetrofitModule {

        private val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }

        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(URLAPITMDB)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
        val moviesAPI: MoviesApi = retrofit.create()
    }

}

const val URLAPITMDB = "https://api.themoviedb.org/"
const val APIKEY = "ab3b42948afb41483b057ded5ba8c586"
const val LANGUAGE = "ru-RU" //сделать значениями по-умолчанию
const val PATHIMAGE = "https://image.tmdb.org/t/p/original"