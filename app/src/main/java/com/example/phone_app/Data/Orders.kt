package com.example.phone_app.Data

import androidx.room.Entity
import com.google.gson.annotations.SerializedName


@Entity(tableName = "products")
data class Orders (
    @SerializedName("Posotita")
    var name: String,
    @SerializedName("UserName")
    var price: Double,
    @SerializedName("Price")
    var quantity: Int,
    @SerializedName("Comments")
    var Comments:String,
    @SerializedName("Time")
    var Time:String
)
