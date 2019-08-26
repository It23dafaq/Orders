package com.example.phone_app.Data


import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "products")

data class Products(



    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("Picture")
    val picture: String,
    @SerializedName("description")
    val description: String

)

