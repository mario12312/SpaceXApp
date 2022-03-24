package com.example.spacexapp.model.rocketdetails


import com.google.gson.annotations.SerializedName

data class ThrustVacuum(
    @SerializedName("kN")
    val kN: Int,
    @SerializedName("lbf")
    val lbf: Int
)