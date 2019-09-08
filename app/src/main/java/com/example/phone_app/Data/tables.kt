package com.example.phone_app.Data

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
@Entity(tableName = "tables")
data class tables (

        @SerializedName("ID")
        var ID: Int,
        @SerializedName("Name")
        val Name: String,
        @SerializedName("TotalPrice")
        val price: Double,
        @SerializedName("Quantity")
        var quantity: String,
        @SerializedName("isOkay")
        var isOkay: Int



)