package com.example.firstproject41182.presenters

import android.location.Location
import android.util.Log
import com.example.firstproject41182.buisness.model.DailyWeatherModel
import com.example.firstproject41182.buisness.model.FeelsLike
import com.example.firstproject41182.buisness.model.SimpleWeatherInfo
import com.example.firstproject41182.buisness.model.Temp
import com.example.firstproject41182.view.Tab2View
import moxy.MvpPresenter

class Tab2Presenter : BasePresenter<Tab2View, List<DailyWeatherModel>>(){

    override fun loadData() {
        if (mLocation!= null){
            repo.get(mLocation?.latitude.toString(), mLocation?.longitude.toString(), "minutely")
                .subscribe(
                    { data = it.daily },
                    {
                        Log.d("RESPONSE_ERROR", "loadData: $it")
                    },
                    { updateWeatherData() }
                )
        }
    }

    override fun updateWeatherData() {
        if (data != null){
            viewState.displayAllDaysRate(data!!)
        }
    }

}