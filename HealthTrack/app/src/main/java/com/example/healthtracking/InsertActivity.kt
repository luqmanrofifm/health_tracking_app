package com.example.healthtracking

import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.healthtracking.databinding.ActivityInsertBinding
import java.util.*

class InsertActivity : AppCompatActivity() {

    private lateinit var b : ActivityInsertBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_insert)

        b.btPickStart.setOnClickListener { pickTimewithPicker(b.tvTimeStart) }
        b.btPickEnd.setOnClickListener { pickTimewithPicker(b.tvTimeEnd) }

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

}