package com.example.phone_app.Data


import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "products")

data class Products(



    @SerializedName("Drink")
    var name: String,
    @SerializedName("Price")
    var price: Double,
    @SerializedName("cuantity")
    var quantity: Int,
    var bottle:Int


)

