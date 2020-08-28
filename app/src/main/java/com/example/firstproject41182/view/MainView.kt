package com.example.firstproject41182.view

import com.example.firstproject41182.buisness.model.HourlyWeatherForecast
import com.example.firstproject41182.buisness.model.WeatherData
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MainView : MvpView {

    @AddToEndSingle
    fun displayMainInfo(weatherData: WeatherData)

    @AddToEndSingle
    fun displayHourlyForecast(data: List<HourlyWeatherForecast>)

    @AddToEndSingle
    fun displayMainBackground(backgroundResource: Int)

}