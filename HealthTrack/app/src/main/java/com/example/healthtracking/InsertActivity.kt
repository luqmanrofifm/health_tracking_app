package com.example.healthtracking

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.healthtracking.databinding.ActivityInsertBinding
import com.example.healthtracking.db.exercise_data.ExerciseDataDB
import com.example.healthtracking.db.exercise_data.ExerciseDataModel
import java.util.*

class InsertActivity : AppCompatActivity() {

    private lateinit var b : ActivityInsertBinding
    private lateinit var insertViewModel: InsertModelView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_insert)

        val dataSource = ExerciseDataDB.getInstance(application).exerciseDataDao
        val viewModelFactory = ExerciseViewModelFactory(dataSource, application)
        insertViewModel = ViewModelProvider(this, viewModelFactory).get(InsertModelView::class.java)

        b.btPickStart.setOnClickListener { pickTimewithPicker(b.tvTimeStart) }
        b.btPickEnd.setOnClickListener { pickTimewithPicker(b.tvTimeEnd) }

        b.btInsert.setOnClickListener {
            val start = convertDateTime(b.tvTimeStart)
            val end = convertDateTime(b.tvTimeEnd)

            val activities = ExerciseDataModel(
                timeAdd = start,
                timeEnd = end,
                distance = b.edtQuantity.text.toString().toInt(),
                type = intent.getStringExtra(EXTRA_TYPE),
                calories = 0,
                sets = 0,
                userId = 0)

            insertViewModel.insert(activities)
        }

        b.btDel.setOnClickListener {
            insertViewModel.delete()
        }

        b.btCek.setOnClickListener {
            insertViewModel.getSpecific("Running")
           // Log.e("coba", insertViewModel.result.value?.timeAdd.toString())
            //b.tvCoba.text = insertViewModel.result.toString()
            insertViewModel.result.observe(this, androidx.lifecycle.Observer {
                b.tvCoba.text = it.timeAdd.toString()
            })
        }

    }

    fun pickTimewithPicker(tv : TextView) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minutes = calendar.get(Calendar.MINUTE)
        TimePickerDialog(this,
            { view, hourOfDay, minute ->
                tv.text = "$hourOfDay:$minute"
            }, hour, minutes, true
        ).show()
    }

    fun convertDateTime(tv1: TextView): Date{
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, 2021)
        calendar.set(Calendar.MONTH, 6)
        calendar.set(Calendar.DAY_OF_MONTH, 3)

        val split = tv1.text.split(":")
        calendar.set(Calendar.HOUR, split[0].toInt())
        calendar.set(Calendar.MINUTE, split[1].toInt())

        return calendar.time

    }

    companion object {
        const val EXTRA_TYPE = "extra_type"
    }

}