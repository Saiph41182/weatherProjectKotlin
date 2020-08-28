package com.example.firstproject41182.buisness.repository

import com.example.firstproject41182.buisness.api.WeatherApiProvider
import com.example.firstproject41182.buisness.model.WeatherData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class ForecastRepo : Repository<WeatherData> {

    private val weatherApi = WeatherApiProvider.weatherApiService

    override fun get(lat: String, lon: String, exclude: String): Observable<WeatherData> {
        return weatherApi.getDailyForecast(lat,lon,exclude)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun add(data: WeatherData) {
        TODO("Not yet implemented")
    }

    override fun remove(data: WeatherData): Boolean {
        TODO("Not yet implemented")
    }

    override fun update(data: WeatherData): Boolean {
        TODO("Not yet implemented")
    }

}