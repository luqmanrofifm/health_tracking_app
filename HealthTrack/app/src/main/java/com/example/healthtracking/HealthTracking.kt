package com.example.healthtracking

import android.app.Application
import com.example.healthtracking.utils.authenticateClient
import com.example.healthtracking.utils.vmWater
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class HealthTracking : Application() {
    override fun onCreate() {
        super.onCreate()

        val modules = arrayListOf(vmWater, authenticateClient)

        startKoin {
            androidLogger()
            androidContext(this@HealthTracking)
            modules(modules)
        }
    }
}