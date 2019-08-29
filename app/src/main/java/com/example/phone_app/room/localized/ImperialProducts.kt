package com.example.phone_app.room.localized

import androidx.room.ColumnInfo

data class ImperialProducts(
    @ColumnInfo(name= "id")
    override val id: Int,
    @ColumnInfo(name= "name")
    override val name: String,
    @ColumnInfo(name= "price")
    override val price: String

):UnitSpecificProducts