package com.example.myweatherapp

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherInterface {
    @GET("/weather/{cityName}")
    fun getWeatherInfo(@Path("cityName") cityInfo: String): Single<WeatherResponse>
}