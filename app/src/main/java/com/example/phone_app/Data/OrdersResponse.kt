package com.example.phone_app.Data

import com.google.gson.annotations.SerializedName

data class OrdersResponse(
    @SerializedName("insertOrders")
    val futureWeatherEntries: Products,
    val location: Products
)