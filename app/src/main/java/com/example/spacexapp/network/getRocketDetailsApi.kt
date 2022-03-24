package com.example.spacexapp.network

import com.example.spacexapp.model.RocketsLaunchesResp
import com.example.spacexapp.model.RocketsLaunchesRespItem
import com.example.spacexapp.model.rocketdetails.RocketLaunchDetailsRespItem
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface getRocketDetailsApi {

    @GET("rockets/")
    suspend fun getRocketDetails(@Query("id") id: String) : Response<ArrayList<RocketLaunchDetailsRespItem>>

    companion object{
        operator fun invoke() : getRocketDetailsApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.spacexdata.com/v4/")
                .build()
                .create(getRocketDetailsApi::class.java)
        }
    }
}