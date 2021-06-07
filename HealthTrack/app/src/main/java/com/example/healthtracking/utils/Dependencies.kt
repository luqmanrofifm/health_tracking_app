package com.example.healthtracking.utils

import com.example.healthtracking.viewmodel.WaterViewModel
import com.example.healthtracking.db.authentication.UserApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val vmWater = module {
    viewModel {
        WaterViewModel()
    }
}

val authenticateClient = module {

    val baseurl = ""

    val instance : UserApi by lazy {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofitBuilder.create(UserApi::class.java)
    }

    single {
        instance
    }
}

class Dependencies : KoinComponent {
    val authClient : UserApi by inject()
}