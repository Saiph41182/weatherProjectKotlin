package com.example.firstproject41182.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.firstproject41182.R
import com.example.firstproject41182.buisness.model.*
import com.example.firstproject41182.util.getImageByUrl
import com.example.firstproject41182.util.provideImage
import com.example.firstproject41182.util.toCelsius
import com.example.firstproject41182.util.toDdMmFormat
import java.lang.StringBuilder

class HourlyWeatherForecastAdapter : RecyclerView.Adapter<HourlyWeatherForecastAdapter.HourlyForecastViewHolder>() {

    private val mData: MutableList<HourlyWeatherForecast> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyForecastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hourly_forecast,parent,false)
        return HourlyForecastViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: HourlyForecastViewHolder, position: Int) {
        holder.bindView(mData[position])
    }

    fun updateData(data: List<HourlyWeatherForecast>){
        if(mData.isEmpty() && (data.isNotEmpty())){
            mData.addAll(data)
        }else {
            mData.clear()
            mData.addAll(data)
        }
        notifyDataSetChanged()
    }


    class HourlyForecastViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @BindView(R.id.hourly_forecast_time) lateinit var time: TextView
        @BindView(R.id.hourly_forecast_image) lateinit var weatherImage: ImageView
        @BindView(R.id.hourly_forecast_temp) lateinit var temp: TextView

        init {
            ButterKnife.bind(this,view)
        }

        fun bindView(model: HourlyWeatherForecast){
            time.text = model.dt.toDdMmFormat("HH:mm")
            weatherImage.setImageResource(model.weather[0].icon.provideImage())
            temp.text = itemView.context.getString(R.string.degree_symbol,model.temp.toCelsius())
        }
    }
}
