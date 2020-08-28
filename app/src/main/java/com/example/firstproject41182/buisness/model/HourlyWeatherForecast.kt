package com.example.firstproject41182.buisness.model

data class HourlyWeatherForecast(val dt: Long,
                                         val temp: Double,
                                         val feels_like: Double,
                                         val pressure: Int,
                                         val humidity: Int,
                                         val dew_point: Double,
                                         val clouds: Int,
                                         val visibility: Int,
                                         val wind_speed: Double,
                                         val wind_deg: Int,
                                         val weather: List<SimpleWeatherInfo>) {

}
