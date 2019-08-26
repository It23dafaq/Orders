package com.example.phone_app.Data.dataBase.Localized

import androidx.room.ColumnInfo
import com.example.phone_app.Data.Products

data class ImperialProducts(
    @ColumnInfo(name= "id")
    override val id: Int,
    @ColumnInfo(name= "name")
    override val name: String,
    @ColumnInfo(name= "price")
    override val price: String

):UnitSpecificProducts