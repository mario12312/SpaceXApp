package com.example.spacexapp.repository

import com.example.spacexapp.network.getRocketsApi
import javax.inject.Inject

class RocketLaunchesRepository(
    private val api: getRocketsApi
) : SafeApiRequest() {

    suspend fun getRockets() = apiRequest { api.getRockets() }
}