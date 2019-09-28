package com.example.phone_app.UI

import android.app.AlertDialog
import android.content.Intent

import android.os.Bundle

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.phone_app.Data.Person
import com.example.phone_app.R
import com.example.phone_app.UI.Admin.AdminActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        login_btn.setOnClickListener {
//
//            Person.email = emailLogin.text.toString()
//            //val intent = Intent(this, AdminActivity::class.java)
//            val intent = Intent(this, MainViewActivity::class.java)
//            intent.putExtra("email", emailLogin.text)
//            startActivity(intent)

            val loginUrl="https://rectifiable-merchan.000webhostapp.com/e_com/login_app_user.php?email="+emailLogin.text.toString()+
                    "&password="+passLogin.text.toString()

            val requestQ = Volley.newRequestQueue(this@LoginActivity)
            val stringRequest = StringRequest(Request.Method.GET,loginUrl, Response.Listener {
                    response ->
                if(response.equals("The user does exist")){
                    val intent = Intent(this, MainViewActivity::class.java)
                    intent.putExtra("email", emailLogin.text)
                    startActivity(intent)
                    Toast.makeText(this@LoginActivity,response,Toast.LENGTH_SHORT).show()
                }else if (response.equals("the user is admin")){
                    val intent = Intent(this, AdminActivity::class.java)
                    intent.putExtra("email", emailLogin.text)
                    startActivity(intent)
                }
                else{
                    createDialog(response)
                }

            },Response.ErrorListener { error->

            })
            requestQ.add(stringRequest)
        }
    }
    private fun createDialog(message: String ) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Message")
        dialogBuilder.setMessage(message)
        dialogBuilder.create().show()
    }
}
