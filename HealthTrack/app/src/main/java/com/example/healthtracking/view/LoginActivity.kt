package com.example.healthtracking.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.healthtracking.R
import com.example.healthtracking.databinding.ActivityLoginBinding
import com.example.healthtracking.db.authentication.UserLoginModel
import com.example.healthtracking.utils.Dependencies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class LoginActivity : AppCompatActivity() {

    //sharedPref editor
    private lateinit var editSP : SharedPreferences.Editor

    //dataBinding
    private lateinit var b : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_login)

        editSP = getSharedPreferences("com.example.healthtracking", Context.MODE_PRIVATE).edit()

        b.loginGuest.setOnClickListener { loginGuest() }
        b.btLogin.setOnClickListener { login() }
        b.signUp.setOnClickListener { goSignUp() }
    }

    fun login() {
        try {
            Dependencies().authClient.login(
                b.email.text.toString(),
                b.password.text.toString()
            ).enqueue(object : Callback<UserLoginModel>{
                override fun onResponse(
                    call: Call<UserLoginModel>,
                    response: Response<UserLoginModel>,
                ) {
                    Toast.makeText(this@LoginActivity, "Logged in successfully", Toast.LENGTH_SHORT).show()
                    editSP.putInt("login", 1).apply()
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                }

                override fun onFailure(call: Call<UserLoginModel>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Failed, please try again", Toast.LENGTH_SHORT).show()
                }
            })
        } catch (e : Exception) {
            Toast.makeText(this, "Failed to connect to server", Toast.LENGTH_SHORT).show()
        }
    }

    fun loginGuest() {
        editSP.putInt("login", 2).apply()
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
    }

    fun goSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

}