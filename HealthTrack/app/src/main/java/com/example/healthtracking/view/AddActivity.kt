package com.example.healthtracking.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.example.healthtracking.R
import com.example.healthtracking.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {

    private lateinit var b : ActivityAddBinding
    val actList = arrayListOf("Running", "Walking", "Cycling", "Hiking")
    val imageArray = arrayListOf(R.drawable.ic_running,
        R.drawable.ic_walk,
        R.drawable.ic_bicycle,
        R.drawable.ic_man_with_bag_and_walking_stick)
    private var actSelected = 0

    private lateinit var type: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_add)
        
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, actList)
        b.listActivity.adapter = arrayAdapter

        b.listActivity.setOnItemClickListener { parent, view, position, id ->
            actSelected = position
            b.activityIcon.setImageResource(imageArray[position])
            type = actList[position]
        }

        b.btAdd.setOnClickListener {
            val intent = Intent(this@AddActivity, InsertActivity::class.java)
            //Log.e("type", type)
            intent.putExtra(InsertActivity.EXTRA_TYPE, type)
            startActivity(intent)
        }

    }

}