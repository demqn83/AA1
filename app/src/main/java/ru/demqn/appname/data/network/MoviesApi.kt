package ru.demqn.appname.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import ru.demqn.appname.utils.ConfigData
import ru.demqn.appname.data.model.ResponseMoviesList
import ru.demqn.appname.data.model.ResponseActors
import ru.demqn.appname.data.model.ResponseMovie

interface MoviesApi {

    @GET("3/movie/now_playing?api_key=${ConfigData.APIKEY}&language=${ConfigData.LANGUAGE}&page=1")
    suspend fun getNowPlaying(): ResponseMoviesList

    @GET("3/movie/{movie_id}?api_key=${ConfigData.APIKEY}&language=${ConfigData.LANGUAGE}")
    suspend fun getMovieById(@Path("movie_id") movieId: Int): ResponseMovie

    @GET("3/movie/{movie_id}/credits?api_key=${ConfigData.APIKEY}&language=${ConfigData.LANGUAGE}")
    suspend fun getPersonId(@Path("movie_id") movieId: Int): ResponseActors

}