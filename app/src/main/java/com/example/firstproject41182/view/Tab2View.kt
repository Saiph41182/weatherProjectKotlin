package com.example.firstproject41182.view

import com.example.firstproject41182.buisness.model.DailyWeatherModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface Tab2View : MvpView {

    @AddToEndSingle
    fun displayAllDaysRate(data: List<DailyWeatherModel>)

}