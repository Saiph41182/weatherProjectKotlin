package com.example.firstproject41182.presenters

import android.util.Log
import com.example.firstproject41182.R
import com.example.firstproject41182.buisness.api.WeatherApi.ExcludedParts
import com.example.firstproject41182.buisness.model.WeatherData
import com.example.firstproject41182.view.MainView

class MainPresenter : BasePresenter<MainView, WeatherData>() {

    override fun loadData() {
        if (mLocation != null) {
            repo.get(
                mLocation?.latitude.toString(),
                mLocation?.longitude.toString(),
                ExcludedParts.excludeThem(ExcludedParts.MINUTELY, ExcludedParts.DAILY)
            )
                .subscribe(
                    { data = it },
                    {
                        Log.d("RESPONSE_ERROR", "loadData: $it")
                    },
                    { updateWeatherData() }
                )
        }
    }

    override fun updateWeatherData() {
        if (data != null) {
            viewState.displayMainInfo(data!!)
            viewState.displayHourlyForecast(data!!.hourly)
        }
        viewState.displayMainBackground(R.drawable.temp_weather_screen_background)
    }
}