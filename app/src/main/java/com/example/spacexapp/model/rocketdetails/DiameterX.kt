package com.example.spacexapp.model.rocketdetails


import com.google.gson.annotations.SerializedName

data class DiameterX(
    @SerializedName("feet")
    val feet: Any,
    @SerializedName("meters")
    val meters: Any
)