package com.example.firstproject41182.buisness.model

data class WeatherData(
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int,
    val current: CurrentWeatherModel,
    val hourly: List<HourlyWeatherForecast>,
    val daily: List<DailyWeatherModel>?)