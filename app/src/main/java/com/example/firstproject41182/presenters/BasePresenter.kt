package com.example.firstproject41182.presenters

import android.location.Location
import com.example.firstproject41182.buisness.repository.ForecastRepo
import moxy.MvpPresenter
import moxy.MvpView
import kotlin.math.abs

abstract class BasePresenter<V: MvpView, D> : MvpPresenter<V>() {
    protected val repo = ForecastRepo()
    protected var data: D? = null

    protected companion object{
        var mLocation: Location? = null
    }

    fun update(){
        loadData()
    }

    fun updateLocation(location: Location){
        if(mLocation == null){
            mLocation = location
            loadData()
        }
        val dy = mLocation!!.latitude - location.latitude
        val dx = mLocation!!.longitude - location.longitude
        if( abs(dy) > 0.3 &&  abs(dx) > 0.3) {
            mLocation = location
            loadData()
        }
    }

    protected abstract fun loadData()

    protected abstract fun updateWeatherData()

}