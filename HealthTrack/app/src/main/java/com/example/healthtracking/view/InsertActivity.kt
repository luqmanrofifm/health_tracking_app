package com.example.healthtracking.view

import android.app.TimePickerDialog
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.healthtracking.viewmodel.ExerciseViewModelFactory
import com.example.healthtracking.viewmodel.InsertModelView
import com.example.healthtracking.R
import com.example.healthtracking.databinding.ActivityInsertBinding
import com.example.healthtracking.db.exercise_data.ExerciseDataDB
import com.example.healthtracking.db.exercise_data.ExerciseDataModel
import com.example.healthtracking.utils.Dependencies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Error
import java.time.LocalDateTime
import java.util.*

class InsertActivity : AppCompatActivity() {

    private lateinit var b : ActivityInsertBinding
    private lateinit var insertViewModel: InsertModelView

    //shared preferences
    private lateinit var sp : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_insert)

        //get sharedPref
        sp = getSharedPreferences("com.example.healthtracking", Context.MODE_PRIVATE)
        val info = sp.getInt("login", 0)


        val dataSource = ExerciseDataDB.getInstance(application).exerciseDataDao
        val viewModelFactory = ExerciseViewModelFactory(dataSource, application)
        insertViewModel = ViewModelProvider(this, viewModelFactory).get(InsertModelView::class.java)

        b.btPickStart.setOnClickListener { pickTimewithPicker(b.tvTimeStart) }
        b.btPickEnd.setOnClickListener { pickTimewithPicker(b.tvTimeEnd) }

        b.btInsert.setOnClickListener {
            saveToDB(info)
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

    fun saveToDB(info : Int) {
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
        if (info == 2) {
            try {
                Dependencies().authClient.uploadExerciseData(
                    timeAdd = start,
                    timeEnd = end,
                    distance = b.edtQuantity.text.toString().toInt(),
                    type = intent.getStringExtra(EXTRA_TYPE)!!,
                    calories = 0,
                    sets = 0,
                    userId = 0
                ).enqueue(object : Callback<ExerciseDataModel>{
                    override fun onResponse(
                        call: Call<ExerciseDataModel>,
                        response: Response<ExerciseDataModel>,
                    ) {
                        Toast.makeText(this@InsertActivity, "Uploaded to server database", Toast.LENGTH_SHORT).show()
                    }

                    override fun onFailure(call: Call<ExerciseDataModel>, t: Throwable) {
                        Toast.makeText(this@InsertActivity, "Can't be uploaded", Toast.LENGTH_SHORT).show()
                    }

                })
            } catch(e : Error) {
                Toast.makeText(this, "Server connection error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val EXTRA_TYPE = "extra_type"
    }

}