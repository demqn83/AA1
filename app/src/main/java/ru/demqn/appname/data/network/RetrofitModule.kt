package ru.demqn.appname.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create
import ru.demqn.appname.utils.ConfigData

@ExperimentalSerializationApi
object RetrofitModule {

    val moviesAPI: MoviesApi

    init {
        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(ConfigData.URLAPITMDB)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
        moviesAPI = retrofit.create()
    }
}