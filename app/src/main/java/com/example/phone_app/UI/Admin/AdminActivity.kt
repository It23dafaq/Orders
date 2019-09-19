package com.example.phone_app.UI.Admin

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.phone_app.Home
import com.example.phone_app.Profile
import com.example.phone_app.R
import com.example.phone_app.Shop

class AdminActivity : AppCompatActivity() {

    private lateinit var textMessage: TextView
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        loadFragment(item.itemId)
        true
    }
    private fun loadFragment(itemId: Int) {
        val tag = itemId.toString()
        val fragment = supportFragmentManager.findFragmentByTag(tag) ?: when (itemId) {
            R.id.nav_Orders -> {
                Admin_Orders.newInstance()
            }
            R.id.nav_filter -> {
                Filter_Admin_fragment.newInstance()
            }
            R.id.nav_byName -> {
                Profile.newInstance()
            }
            else -> {
                null
            }
        }

        // replace fragment
        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer1, fragment as Fragment, tag)
                .commit()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin2)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)


        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}
