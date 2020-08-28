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

class ThreeDaysForecastAdapter : RecyclerView.Adapter<ThreeDaysForecastAdapter.ThreeDaysRateViewHoled>() {

    private val mData: MutableList<DailyWeatherModel> = ArrayList(3)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThreeDaysRateViewHoled {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_free_days_rate_forecast,parent,false)
        return ThreeDaysRateViewHoled(view)
    }

    override fun onBindViewHolder(holder: ThreeDaysRateViewHoled, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun updateData(data: List<DailyWeatherModel>){
        if(mData.isEmpty() && (data.isNotEmpty())){
            mData.addAll(data)
        }else {
            mData.clear()
            mData.addAll(data)
        }
        notifyDataSetChanged()
    }

    class ThreeDaysRateViewHoled(view: View) : RecyclerView.ViewHolder(view){
        @BindView(R.id.image_weather_itdrf) lateinit var image: ImageView
        @BindView(R.id.weather_description_ifdrf) lateinit var description: TextView
        @BindView(R.id.daily_weather_temp_ifdrf) lateinit var temp: TextView
        @BindView(R.id.daily_date_ifdrf) lateinit var date: TextView
        @BindView(R.id.daily_weather_temp_max_ifdrf) lateinit var tempMax: TextView
        @BindView(R.id.daily_weather_temp_min_ifdrf) lateinit var tempMin: TextView
        @BindView(R.id.daily_pressure_ifdrf) lateinit var pressure: TextView
        @BindView(R.id.daily_wind_speed__ifdrf) lateinit var windSpeed: TextView
        @BindView(R.id.daily_humidity__ifdrf) lateinit var humidity: TextView
        @BindView(R.id.daily_wind_dir__ifdrf) lateinit var windDir: TextView

        init {
            ButterKnife.bind(this,view)
        }

        fun bind(data: DailyWeatherModel){
            image.setImageResource(data.weather[0].icon.provideImage())
            description.text = data.weather[0].description
            temp.text = itemView.context.getString(R.string.degree_symbol,data.temp.day.toCelsius())
            date.text = data.dt.toDdMmFormat("dd.MM")
            tempMax.text = itemView.context.getString(R.string.degree_symbol,data.temp.min.toCelsius())
            tempMin.text = itemView.context.getString(R.string.degree_symbol,data.temp.max.toCelsius())
            pressure.text = data.pressure.toString()
            windSpeed.text = data.wind_speed.toString()
            humidity.text = data.humidity.toString()
            windDir.text = data.wind_deg.toString()
        }

    }


}
