package com.example.spacexapp.repository

import com.example.spacexapp.network.getRocketDetailsApi

class RocketLaunchDetailsRepository(
    private val api: getRocketDetailsApi
): SafeApiRequest() {

    suspend fun getRocketDetails(id: String) = apiRequest { api.getRocketDetails(id) }
}