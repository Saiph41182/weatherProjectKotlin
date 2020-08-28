package com.example.firstproject41182.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import butterknife.BindView
import butterknife.ButterKnife
import com.example.firstproject41182.R
import com.example.firstproject41182.buisness.model.*
import com.example.firstproject41182.presenters.MainPresenter
import com.example.firstproject41182.util.provideImage
import com.example.firstproject41182.util.toCelsius
import com.example.firstproject41182.util.toDdMmFormat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

const val GPS_PERMISSION_CODE = 1

class MainActivity : MvpAppCompatActivity(R.layout.activity_main), MainView {

    @BindView(R.id.tab_layout)
    lateinit var tabs: TabLayout
    @BindView(R.id.view_pager)
    lateinit var viewPager: ViewPager2
    @BindView(R.id.image_weather_itdrf)
    lateinit var image: ImageView
    @BindView(R.id.main_city)
    lateinit var city: TextView
    @BindView(R.id.main_country)
    lateinit var country: TextView
    @BindView(R.id.main_date)
    lateinit var date: TextView
    @BindView(R.id.weather_description_ifdrf)
    lateinit var weatherDescription: TextView
    @BindView(R.id.daily_weather_temp_ifdrf)
    lateinit var temp: TextView
    @BindView(R.id.daily_weather_temp_max_ifdrf)
    lateinit var tempMax: TextView
    @BindView(R.id.daily_weather_temp_min_ifdrf)
    lateinit var tempMin: TextView
    @BindView(R.id.main_hourly_forecast)
    lateinit var hourlyForecastRv: RecyclerView
    @BindView(R.id.main_screen_container)
    lateinit var mainContainer: CoordinatorLayout
    @BindView(R.id.bottom_sheets_layout)
    lateinit var bottomSheets: CoordinatorLayout

    private val mainPresenter by moxyPresenter { MainPresenter() }
    private val locationService by lazy { LocationServices.getFusedLocationProviderClient(this) }
    private val locationRequest by lazy{ initLocationRequest() }
    private lateinit var sheetsBehavior: BottomSheetBehavior<CoordinatorLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ButterKnife.bind(this)

        checkPermissions()

        sheetsBehavior = BottomSheetBehavior.from(bottomSheets)


        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        adapter.addFragment(Tab1Fragment())
        adapter.addFragment(Tab2Fragment())
        viewPager.adapter = adapter

        tabs.addOnTabSelectedListener(tabOnClickListener)

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "3 days"
                1 -> tab.text = "5 days"
            }
        }.attach()

        initTempRV(hourlyForecastRv)


    }
    @SuppressLint("MissingPermission")
    override fun onStart() {
        super.onStart()
        locationService.requestLocationUpdates(locationRequest, callback, null)
    }


    override fun onResume() {
        super.onResume()
        mainPresenter.update()
    }

    override fun onPause() {
        super.onPause()

    }
    @SuppressLint("MissingPermission")
    override fun onStop() {
        super.onStop()
        locationService.removeLocationUpdates(callback)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == GPS_PERMISSION_CODE) {
            if (permissions.isNotEmpty()) {
                Toast.makeText(this, "Thank you", Toast.LENGTH_SHORT).show()
                locationService.requestLocationUpdates(locationRequest, callback, null)
            }
        }
    }

    override fun displayMainInfo(weatherData: WeatherData) {
        val tempAmplitude = weatherData.hourly
            .asSequence()
            .takeWhile { it.dt.toDdMmFormat("HH:mm") != "00:00"}
            .takeIf { it -> it.minBy { it.temp }?.temp != null }
            .takeIf { it ->  it?.maxBy { it.temp}?.temp != null }
            ?.map { it.temp }
            ?.toList()
        city.text = weatherData.timezone.substringAfter("/")
        country.text = weatherData.timezone.substringBefore("/")
        date.text = weatherData.current.dt.toDdMmFormat("dd.MM")
        weatherDescription.text = weatherData.current.weather[0].description
        temp.text = getString(R.string.degree_symbol,weatherData.current.temp.toCelsius())
        tempMax.text = getString(R.string.degree_symbol,tempAmplitude?.max()?.toCelsius())
        tempMin.text = getString(R.string.degree_symbol,tempAmplitude?.min()?.toCelsius())
        image.setImageResource(weatherData.current.weather[0].icon.provideImage())
    }

    override fun displayHourlyForecast(data: List<HourlyWeatherForecast>) {
        val adapter = hourlyForecastRv.adapter as (HourlyWeatherForecastAdapter)
        adapter.updateData(data)
    }

    override fun displayMainBackground(backgroundResource: Int) {
        mainContainer.background =  ResourcesCompat.getDrawable(this.resources,backgroundResource,this.theme)
    }

    private fun initLocationRequest() : LocationRequest {
        val request = LocationRequest.create()
        return request.apply {
            interval = 60000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    private fun checkPermissions() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            MaterialAlertDialogBuilder(this)
                .setTitle("We need your GPS data")
                .setMessage("Please switch on your GPS connection to continue our work")
                .setPositiveButton("Ok") { _, _ ->
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        GPS_PERMISSION_CODE
                    )
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                        GPS_PERMISSION_CODE
                    )
                }
                .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
                .create()
                .show()
        }
    }

    private fun initTempRV(rv: RecyclerView) {
        rv.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = HourlyWeatherForecastAdapter()
            addItemDecoration(PaddingItemDecorator(this@MainActivity))
        }
    }

    private val callback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult?) {
            if (p0 != null) {
                for (location in p0.locations) {
                    if (location != null) {
                        mainPresenter.updateLocation(location)
                        Log.d("RESPONSE_ERROR", "onLocationResult: lat ${location.latitude} , lon ${location.longitude}")
                    }
                }
            }
        }
    }
    private val tabOnClickListener = object : TabLayout.OnTabSelectedListener{
        override fun onTabSelected(tab: TabLayout.Tab?) {
            if(sheetsBehavior.state != BottomSheetBehavior.STATE_EXPANDED){
                sheetsBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
        }

        override fun onTabReselected(tab: TabLayout.Tab?) {
            if(sheetsBehavior.state != BottomSheetBehavior.STATE_EXPANDED){
                sheetsBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
    }



}
/*val display = windowManager.defaultDisplay
        val point = Point()

        display.getSize(point)


        val shape = ShapeDrawable(RectShape())
        val h = point.y

        shape.paint.shader =
            LinearGradient(0f,0f,0f,h.toFloat()/4,Color.YELLOW, Color.WHITE, Shader.TileMode.CLAMP)
        mainContainer.background = shape*/