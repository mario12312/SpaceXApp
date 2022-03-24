package com.example.spacexapp.model.rocketdetails


import com.google.gson.annotations.SerializedName

data class Height(
    @SerializedName("feet")
    val feet: Float,
    @SerializedName("meters")
    val meters: Float
)