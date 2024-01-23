package com.hsbc.temperatureapp.data.network

import com.hsbc.temperatureapp.TemperatureData
import com.hsbc.temperatureapp.TemperatureResponse
import retrofit2.Response
import retrofit2.http.GET

interface TemperatureApi {
    @GET("temperature.json")
    suspend fun getTemperatureData(): Response<TemperatureResponse>
}