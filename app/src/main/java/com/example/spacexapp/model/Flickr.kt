package com.example.spacexapp.model


import com.google.gson.annotations.SerializedName

data class Flickr(
    @SerializedName("original")
    val original: List<Any>,
    @SerializedName("small")
    val small: List<Any>
)