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
import com.example.firstproject41182.buisness.model.DailyWeatherModel
import com.example.firstproject41182.util.getImageByUrl
import com.example.firstproject41182.util.provideImage
import com.example.firstproject41182.util.toCelsius
import com.example.firstproject41182.util.toDdMmFormat
import java.lang.StringBuilder

class AllDaysForecastAdapter : RecyclerView.Adapter<AllDaysForecastAdapter.AllDaysRateViewHoled>() {

    private val mData: MutableList<DailyWeatherModel> = ArrayList(7)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllDaysRateViewHoled {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_all_days_forecast,parent,false)
        return AllDaysRateViewHoled(view)
    }

    override fun onBindViewHolder(holder: AllDaysRateViewHoled, position: Int) {
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

    class AllDaysRateViewHoled(view: View) : RecyclerView.ViewHolder(view){
        @BindView(R.id.all_days_weather_image_iadrf) lateinit var image: ImageView
        @BindView(R.id.all_days_temp_iadrf) lateinit var temp: TextView
        @BindView(R.id.all_daily_date_iadrf) lateinit var date: TextView
        @BindView(R.id.all_days_min_temp_iadrf) lateinit var tempMin: TextView

        init {
            ButterKnife.bind(this,view)
        }

        fun bind(data: DailyWeatherModel){
            image.setImageResource(data.weather[0].icon.provideImage())
            temp.text = itemView.context.getString(R.string.degree_symbol,data.temp.max.toCelsius())
            date.text = data.dt.toDdMmFormat("dd.MM")
            tempMin.text = itemView.context.getString(R.string.degree_symbol,data.temp.min.toCelsius())
        }

    }


}
