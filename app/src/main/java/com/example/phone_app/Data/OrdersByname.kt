package com.example.phone_app.Data

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Orders")
data class OrdersByname (
    @SerializedName("Posotita")
    var posotita: String,
    @SerializedName("UserName")
    var Username:String,
    @SerializedName("DrinkName")
    var Drink:String,
    @SerializedName("Price")
    var price: Double,
    @SerializedName("Time")
    var Time:String,
    @SerializedName("TotalPrice")
    var TotalPrice:Double
)