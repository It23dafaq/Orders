package com.example.phone_app.UI.Admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.phone_app.R
import com.example.phone_app.UI.Adapters.OrderAdapter
import com.example.phone_app.UI.ViewModelFactory.HomeViewModelFactory
import com.example.phone_app.UI.ViewModels.HomeViewModel
import kotlinx.android.synthetic.main.activity_daily_orders.*
import kotlinx.android.synthetic.main.order_daily.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class Filter_Admin_fragment : Fragment() ,KodeinAware{
    override val kodein by closestKodein()
    private val viewModelFactory: HomeViewModelFactory by instance()
    private lateinit var viewModel: HomeViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.order_daily, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)

        viewModel.getOrders("Today")

        viewModel.ordersDaily.observe(this, Observer {

            val adapter = OrderAdapter(it)
            da_recy.adapter = adapter
            da_recy.layoutManager = LinearLayoutManager(this.context)
        })


    }


    companion object {

        @JvmStatic
        fun newInstance() =
            Filter_Admin_fragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
