package com.example.firstproject41182.view

import com.example.firstproject41182.buisness.model.DailyWeatherModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface Tab1View : MvpView {

    @AddToEndSingle
    fun displayThreeDaysRate(data: List<DailyWeatherModel>)

}