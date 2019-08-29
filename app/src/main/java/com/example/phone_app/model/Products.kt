package com.example.phone_app.model


import androidx.room.Entity
import com.google.gson.annotations.SerializedName


@Entity(tableName = "products")

data class Products(



    @SerializedName("Drink")
    var name: String,
    @SerializedName("Price")
    val price: Double,
    @SerializedName("cuantity")
    var quantity: String


)

