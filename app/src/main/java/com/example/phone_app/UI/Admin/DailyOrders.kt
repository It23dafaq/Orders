package com.example.phone_app.UI.Admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.phone_app.Network.ConnectivityInterceptorImpl
import com.example.phone_app.Network.OrderApi
import com.example.phone_app.Network.OrderNetworkDatasourceImpl
import com.example.phone_app.R
import com.example.phone_app.UI.Adapters.OrderAdapter
import kotlinx.android.synthetic.main.activity_daily_orders.*


class DailyOrders : AppCompatActivity() {

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
