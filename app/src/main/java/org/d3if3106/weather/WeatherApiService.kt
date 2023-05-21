package org.d3if3106.weather

import android.telecom.Call as TelecomCall
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("weather")
    fun getCurrentWeather(
        @Query("q") location: String,
        @Query("appid") apiKey: String
    ): Call<WeatherResponse>
}
