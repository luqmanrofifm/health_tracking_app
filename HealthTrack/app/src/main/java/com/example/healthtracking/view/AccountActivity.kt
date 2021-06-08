package com.example.healthtracking.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.healthtracking.R
import com.example.healthtracking.db.user_health.UserHealthModel
import com.example.healthtracking.utils.Dependencies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import com.example.healthtracking.databinding.ActivityAccountBinding

class AccountActivity : AppCompatActivity() {

    private lateinit var b : ActivityAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_account)

        b.update.setOnClickListener { updateData() }
        b.logOut.setOnClickListener { logOut() }
    }

    fun updateData() {
        Dependencies().authClient.updateUserData(
            Calendar.getInstance().time,
            b.userId.text.toString().toInt(),
            b.weight.text.toString().toInt(),
            b.height.text.toString().toInt(),
            b.pulse.text.toString().toInt()
        ).enqueue(object : Callback<UserHealthModel>{
            override fun onResponse(
                call: Call<UserHealthModel>,
                response: Response<UserHealthModel>,
            ) {
                Toast.makeText(this@AccountActivity, "Uploaded", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@AccountActivity, AccountActivity::class.java)
                startActivity(intent)
            }

            override fun onFailure(call: Call<UserHealthModel>, t: Throwable) {
                Toast.makeText(this@AccountActivity, "Failed to upload", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun logOut() {
        val sharedPref = getSharedPreferences("com.example.healthtracking", Context.MODE_PRIVATE)
        sharedPref.edit().putInt("login", 0).apply()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}