package com.hsbc.temperatureapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsbc.temperatureapp.domain.GetRandomTemperatureUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TemperatureViewModel @Inject constructor(
    private val getTemperatureUseCase: GetRandomTemperatureUseCase,
) : ViewModel() {

   private val _currentTemperature = MutableLiveData<TemperatureData>()
   val currentTemperature: LiveData<TemperatureData> get() = _currentTemperature

    fun fetchTemperatureData() {
        viewModelScope.launch {
            try {
                val result = getTemperatureUseCase.getRandomTemperature()
                _currentTemperature.value = result
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}


