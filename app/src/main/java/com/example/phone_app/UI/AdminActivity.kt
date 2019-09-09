package com.example.phone_app.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.phone_app.R
import com.example.phone_app.UI.Admin.DailyOrders
import kotlinx.android.synthetic.main.activity_admin.*


class AdminActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        Daily.setOnClickListener {
            val intent = Intent(this, DailyOrders::class.java)
            startActivity(intent)
        }
    }
}
