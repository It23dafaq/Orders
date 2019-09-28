package com.example.phone_app.UI.Admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phone_app.Network.ConnectivityInterceptorImpl
import com.example.phone_app.Network.OrderApi
import com.example.phone_app.Network.OrderNetworkDatasource
import com.example.phone_app.Network.OrderNetworkDatasourceImpl
import com.example.phone_app.R
import com.example.phone_app.UI.Adapters.OrderAdapter
import com.example.phone_app.UI.ViewModelFactory.HomeViewModelFactory
import kotlinx.android.synthetic.main.activity_daily_orders.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class DailyOrders : AppCompatActivity(),KodeinAware {

    override val kodein by closestKodein()
    private val orderApi: OrderApi by instance()
    private val oderdata: OrderNetworkDatasource by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_orders)


        val orderService = OrderApi.invoke(ConnectivityInterceptorImpl(baseContext))
        val productNetworkDataSource = OrderNetworkDatasourceImpl(orderService)
        productNetworkDataSource.downloadProduct.observe(this@DailyOrders, Observer {

            val adapter = OrderAdapter(it)
            order_rec.adapter = adapter
            order_rec.layoutManager = LinearLayoutManager(this.baseContext)
        })

    }
}
