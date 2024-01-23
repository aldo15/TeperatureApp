package com.hsbc.temperatureapp.domain

import com.hsbc.temperatureapp.TemperatureData
import com.hsbc.temperatureapp.data.TemperatureRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class GetRandomTemperatureUseCaseTest{
    @RelaxedMockK
    private lateinit var temperatureRepository: TemperatureRepository
    lateinit var getRandomTemperatureUseCase: GetRandomTemperatureUseCase
    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getRandomTemperatureUseCase = GetRandomTemperatureUseCase(temperatureRepository)
    }


    @Test
    fun `when the api doesnt return anything then get default value or emtylist`() = runBlocking{
        coEvery { temperatureRepository.getTemperatureData() } returns emptyList()
         getRandomTemperatureUseCase.getRandomTemperature()
        coVerify(exactly = 1){ temperatureRepository.getTemperatureData() }
    }

    @Test
    fun `when the api return emtylist then return default TemperatureData object`() = runBlocking {
        coEvery { temperatureRepository.getTemperatureData() } returns emptyList()
        val result = getRandomTemperatureUseCase.getRandomTemperature()
        assert(result.place.isEmpty() && result.value == 0 && result.unit.isEmpty() )
    }

    @Test
    fun `when the apis execute is success and return random TemperatureData object`() = runBlocking {
        val listTemperature : List<TemperatureData> = listOf( TemperatureData("Mexico", 28, "C"))
        coEvery { temperatureRepository.getTemperatureData() } returns listTemperature
        val result = getRandomTemperatureUseCase.getRandomTemperature()
        assert(result == listTemperature.first())
    }
}