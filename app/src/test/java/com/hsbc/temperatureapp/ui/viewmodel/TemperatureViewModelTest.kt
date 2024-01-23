package com.hsbc.temperatureapp.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hsbc.temperatureapp.TemperatureData
import com.hsbc.temperatureapp.TemperatureViewModel
import com.hsbc.temperatureapp.domain.GetRandomTemperatureUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class TemperatureViewModelTest{
    @RelaxedMockK
    private lateinit var getRandomTemperatureUseCase: GetRandomTemperatureUseCase
    private lateinit var temperatureViewModel: TemperatureViewModel

    @get:Rule
    var rule:InstantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        temperatureViewModel = TemperatureViewModel(getRandomTemperatureUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `when getRandomTemperatureUseCase return a Temperature object, it's have to set on the livedata`() = runTest{
        val temperature =  TemperatureData("Canada", 10, "C")
        //Given
        coEvery { getRandomTemperatureUseCase.getRandomTemperature() } returns temperature
        //When
        temperatureViewModel.fetchTemperatureData()
        //Then
        assert(temperatureViewModel.currentTemperature.value == temperature)
    }
}