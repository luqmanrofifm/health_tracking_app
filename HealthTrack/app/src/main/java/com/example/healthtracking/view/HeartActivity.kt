package com.example.healthtracking.view

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.healthtracking.R

class HeartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heart)
        if (checkPermission()  == PackageManager.PERMISSION_DENIED){
            requestPermissions()
        }



    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (checkPermission() == PackageManager.PERMISSION_DENIED)
            onBackPressed()
    }

    fun checkPermission() : Int {
        return ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
    }

    fun requestPermissions() {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 123)
    }
}