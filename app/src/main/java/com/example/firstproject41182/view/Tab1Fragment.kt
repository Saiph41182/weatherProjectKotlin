package com.example.firstproject41182.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.firstproject41182.R
import com.example.firstproject41182.buisness.model.DailyWeatherModel
import com.example.firstproject41182.presenters.Tab1Presenter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
const val TAG = "111321"
class Tab1Fragment : MvpAppCompatFragment(), Tab1View {

    @BindView(R.id.free_days_forecast_rv)
    lateinit var list: RecyclerView
    private lateinit var sheetBehavior: BottomSheetBehavior<CoordinatorLayout>

    private val presenter by moxyPresenter { Tab1Presenter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_free_days_rate, container, false)

        sheetBehavior = BottomSheetBehavior.from((activity as MainActivity).bottomSheets)


        ButterKnife.bind(this, view)

        initTempRV()
        Log.d(TAG, "onCreateView: ")

        return view
    }

    private fun initTempRV() {
        list.apply {
            layoutManager =
                LinearLayoutManager(list.context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = ThreeDaysForecastAdapter()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.update()
        Log.d(TAG, "onResume: ")
    }

    override fun displayThreeDaysRate(data: List<DailyWeatherModel>) {
        val adapter = list.adapter as ThreeDaysForecastAdapter
        adapter.updateData(data.subList(0, 4))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach: ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated: ")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach: ")
    }

}