package com.example.phone_app.ui

import android.content.Intent

import android.os.Bundle

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.phone_app.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        goLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        btn_register.setOnClickListener {
            if(password.text.toString().equals(confirmpassword.text.toString())){
                val SignUpUrl = "https://rectifiable-merchan.000webhostapp.com/e_com/join_new_user.php?email="+email.text.toString()+
                        "&username="+name.text.toString()+"&password="+password.text.toString()

                val requestQ = Volley.newRequestQueue(this@RegisterActivity)
                val stringRequest = StringRequest(Request.Method.GET,SignUpUrl,Response.Listener{ response ->
                    if(response.equals("A user with this email Addres already exists"))
                        {
                            createDialog(response)
                        }else {
                        createDialog(response)
                    }




                },Response.ErrorListener { error ->
                    createDialog(error.message.toString())
                })
                requestQ.add(stringRequest)

            }else{
                createDialog("Password Mismatch")
            }

        }

    }

   // btn_register
    private fun createDialog(message: String ) {
       val dialogBuilder = AlertDialog.Builder(this)
       dialogBuilder.setTitle("Alert")
       dialogBuilder.setMessage(message)
       dialogBuilder.create().show()
    }
}
