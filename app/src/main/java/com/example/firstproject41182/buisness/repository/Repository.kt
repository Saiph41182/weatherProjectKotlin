package com.example.firstproject41182.buisness.repository

import com.example.firstproject41182.buisness.api.WeatherApi
import com.example.firstproject41182.buisness.model.WeatherData
import io.reactivex.rxjava3.core.Observable

interface Repository<T> {

    fun get(lat: String, lon: String, exclude: String): Observable<WeatherData>

    fun add(data: T)

    fun remove(data: T) : Boolean

    fun update(data: T) : Boolean

}