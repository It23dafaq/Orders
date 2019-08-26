package com.example.phone_app.Data

import androidx.room.Entity
import com.google.gson.annotations.SerializedName
@Entity(tableName = "tables")
data class tables (

        @SerializedName("TableID")
        var ID: Int,
        @SerializedName("TotalPrice")
        val price: Double,
        @SerializedName("quantity")
        var quantity: String,
        @SerializedName("isOkay")
        var isOkay: Boolean



)