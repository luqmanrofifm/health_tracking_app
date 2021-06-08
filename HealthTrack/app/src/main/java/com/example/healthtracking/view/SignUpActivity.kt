package com.example.healthtracking.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.healthtracking.R
import com.example.healthtracking.databinding.ActivitySignUpBinding
import com.example.healthtracking.db.authentication.UserCreateModel
import com.example.healthtracking.utils.Dependencies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    //databinding
    private lateinit var b : ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        b.signUp.setOnClickListener {
            if (checkId()) {
                signup()
            } else {
                Toast.makeText(this, "pastikan username, email, dan password benar", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun checkId() : Boolean {
        return b.username.text.toString().length > 8 && b.password.text.length > 8 && Patterns.EMAIL_ADDRESS.matcher(b.email.text).matches()
    }

    fun signup() {
        try {
            Dependencies().authClient.signUp(
                b.username.text.toString(),
                b.email.text.toString(),
                b.password.text.toString()
            ).enqueue(object : Callback<UserCreateModel>{
                override fun onResponse(
                    call: Call<UserCreateModel>,
                    response: Response<UserCreateModel>,
                ) {
                    Toast.makeText(this@SignUpActivity, "Account created, please log in", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                    startActivity(intent)
                }

                override fun onFailure(call: Call<UserCreateModel>, t: Throwable) {
                    Toast.makeText(this@SignUpActivity, "Account can't be created", Toast.LENGTH_SHORT).show()
                }
            })
        } catch (e : Exception) {
            Toast.makeText(this, "Failed to connect to server", Toast.LENGTH_SHORT).show()
            Log.d("error", "bikin akun")
        }
    }
}