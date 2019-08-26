package com.example.phone_app.OtherTraining

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.phone_app.R
import kotlinx.android.synthetic.main.activity_show_all_json.*

class ShowAllJSON : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_all_json)

        textView2.setOnClickListener {

            val allProductsUrl = "http://192.168.1.2/e_com/present_json_array.php"

            var allProducts: String = ""

            val requestQ: RequestQueue = Volley.newRequestQueue(this@ShowAllJSON)
            val jsonAR = JsonArrayRequest(Request.Method.GET,allProductsUrl,null, Response.Listener { response ->

                        for(productIndex in 1.until(response.length())){
                            allProducts +=response.getJSONObject(productIndex).getString("name")+ " -"+response.getJSONObject(productIndex).getInt("price")


                        }

                allproductstext.text= allProducts

            },Response.ErrorListener { error ->

                allproductstext.text = error.message
            })

            requestQ.add(jsonAR)
        }
    }
}
