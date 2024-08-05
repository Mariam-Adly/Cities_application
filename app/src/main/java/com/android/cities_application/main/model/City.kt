package com.android.cities_application.main.model

import com.google.gson.annotations.SerializedName

data class City(
    val country: String,
    val name: String,
    @SerializedName("_id")
    val id: Long,
    val coord: Coordinates,
)

data class Coordinates(
    val lon: Double,
    val lat: Double,
)