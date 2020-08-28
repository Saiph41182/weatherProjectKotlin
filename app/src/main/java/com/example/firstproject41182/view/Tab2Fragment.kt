package com.example.firstproject41182.view

import android.content.Context
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.firstproject41182.R
import com.example.firstproject41182.buisness.model.DailyWeatherModel
import com.example.firstproject41182.presenters.Tab2Presenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class Tab2Fragment : MvpAppCompatFragment(), Tab2View{

    @BindView(R.id.all_days_forecast_rv) lateinit var list: RecyclerView

    private val presenter by moxyPresenter{ Tab2Presenter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_all_days_rate,container,false)


        ButterKnife.bind(this,view)

        initTempRV(view)

        return view
    }

    private fun initTempRV(view: View) {
        val rv: RecyclerView = view.findViewById(R.id.all_days_forecast_rv)
        rv.apply {
            layoutManager =
                LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = AllDaysForecastAdapter()

        }
    }

    override fun displayAllDaysRate(data: List<DailyWeatherModel>) {
        val adapter = list.adapter as AllDaysForecastAdapter
        adapter.updateData(data)
    }

    override fun onResume() {
        super.onResume()
        presenter.update()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }
}