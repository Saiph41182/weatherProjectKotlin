package com.example.firstproject41182.presenters

import android.util.Log
import com.example.firstproject41182.buisness.model.DailyWeatherModel
import com.example.firstproject41182.view.Tab1View

class Tab1Presenter : BasePresenter<Tab1View,List<DailyWeatherModel>>() {

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
            viewState.displayThreeDaysRate(data!!)
        }
    }


}