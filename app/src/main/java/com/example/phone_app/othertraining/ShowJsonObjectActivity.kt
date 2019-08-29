package com.example.phone_app.othertraining


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.phone_app.R
import kotlinx.android.synthetic.main.activity_show_json_object.*

class ShowJsonObjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_json_object)

        btn_submit.setOnClickListener {

            val productUrl= "http://192.168.137.1/e_com/present_json.php?id="+idinput.text.toString()
            val requestQ: RequestQueue = Volley.newRequestQueue(this@ShowJsonObjectActivity)
            val jsonOR = JsonObjectRequest(Request.Method.GET,productUrl,null,
                Response.Listener{ response ->

                showname.text = response.getString("name")
                    pricetext.text = response.getString("price").toString()




            }, Response.ErrorListener { error ->

              showname.text = error.message
            })
            requestQ.add(jsonOR)
            }
        }
    }

