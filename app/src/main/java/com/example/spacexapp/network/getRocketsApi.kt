package com.example.spacexapp.network

import com.example.spacexapp.model.RocketsLaunchesResp
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface getRocketsApi {

    @GET("launches/")
    suspend fun getRockets() : Response<RocketsLaunchesResp>

    companion object{
        operator fun invoke() : getRocketsApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.spacexdata.com/v4/")
                .build()
                .create(getRocketsApi::class.java)
        }
    }
}