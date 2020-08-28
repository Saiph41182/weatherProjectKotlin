package com.example.firstproject41182.buisness.model

data class DailyWeatherModel(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: Temp,
    val feels_like: FeelsLike,
    val pressure: Int,
    val humidity: Int,
    val dew_point: Double,
    val wind_speed: Double,
    val wind_deg: Int,
    val weather: List<SimpleWeatherInfo>,
    val uvi: Double,
    val clouds: Int)
{

}

data class Temp(
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double,
    val eve: Double,
    val morn: Double) {}

data class FeelsLike(
    val day: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
) {}
