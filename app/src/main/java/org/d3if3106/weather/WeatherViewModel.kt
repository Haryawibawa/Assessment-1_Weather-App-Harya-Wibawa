package org.d3if3106.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WeatherViewModel : ViewModel() {
    private val _weatherData = MutableLiveData<Weather>()
    val weatherData: LiveData<Weather>
        get() = _weatherData

    // Metode ini digunakan untuk mengupdate data cuaca
    fun updateWeather(weather: Weather) {
        _weatherData.value = weather
    }
}
