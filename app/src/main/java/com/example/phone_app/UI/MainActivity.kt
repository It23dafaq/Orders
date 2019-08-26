package com.example.phone_app.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.phone_app.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_data.setOnClickListener{
            val serverUrl:String = "https://192.168.1.1/e_com/present_json_array.php"
            var requestQ: RequestQueue = Volley.newRequestQueue(this@MainActivity)
            var stringRequst = StringRequest(Request.Method.GET,serverUrl,Response.Listener { response ->  showdata.text=response},
                Response.ErrorListener { error-> showdata.text=error.message})
             requestQ.add(stringRequst)
        }
    }
}
