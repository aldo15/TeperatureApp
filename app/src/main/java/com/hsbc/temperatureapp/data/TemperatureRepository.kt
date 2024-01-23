package com.hsbc.temperatureapp.data

import com.hsbc.temperatureapp.TemperatureData
import com.hsbc.temperatureapp.data.network.TemperatureService
import javax.inject.Inject

class TemperatureRepository @Inject constructor(
    private val api: TemperatureService,
) {
    suspend fun getTemperatureData(): List<TemperatureData> {
        val response = api.getTemperatures().data
        return response
    }
}