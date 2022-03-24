package com.example.spacexapp.model


import com.google.gson.annotations.SerializedName

data class Patch(
    @SerializedName("large")
    val large: Any,
    @SerializedName("small")
    val small: Any
)