package com.hsbc.temperatureapp.data.network

import com.hsbc.temperatureapp.TemperatureResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TemperatureService @Inject constructor(private val api:TemperatureApi){

    suspend fun getTemperatures(): TemperatureResponse{
        return withContext(Dispatchers.IO){
            val response = api.getTemperatureData()
            response.body() ?:TemperatureResponse(emptyList(), "")
        }
    }
}