package com.example.phone_app.room.localized.dao


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.phone_app.model.Products
import com.example.phone_app.room.localized.ImperialProducts

//database access objects is always interfaces Room documentation
@Dao
interface ProductsDao {
   //if some comflict like same id -> replace
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //update or insert
    fun upsert(productEntry:Products)
    @Query("select * from products")
    fun getProducts(): LiveData<ImperialProducts>
}