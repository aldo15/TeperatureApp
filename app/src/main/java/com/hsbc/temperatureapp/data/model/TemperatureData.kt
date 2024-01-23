package com.hsbc.temperatureapp

import com.google.gson.annotations.SerializedName

data class TemperatureData(
    @SerializedName("place") var place: String,
    @SerializedName("value") val value: Int,
    @SerializedName("unit") val unit: String
)

data class TemperatureResponse(
    @SerializedName("data") val data: List<TemperatureData>,
    @SerializedName("recordTime") val recordTime: String
)