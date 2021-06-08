package com.example.healthtracking.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WaterViewModel : ViewModel() {
    val water = MutableLiveData<Int>()

    init {
        water.value = 0
    }

    fun onPlus() {
        water.value = water.value?.plus(1)
    }
}