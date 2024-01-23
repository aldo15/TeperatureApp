package com.hsbc.temperatureapp.domain

import com.hsbc.temperatureapp.TemperatureData
import com.hsbc.temperatureapp.data.TemperatureRepository
import javax.inject.Inject

class GetRandomTemperatureUseCase @Inject constructor(private val repository: TemperatureRepository) {
    suspend fun getRandomTemperature() : TemperatureData {
        val result = repository.getTemperatureData()
        if(result.isNotEmpty()){
            return result.random()
        } else {
            return TemperatureData("", 0, "")
        }
    }
}