package com.example.healthtracking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import com.example.healthtracking.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    //Data Binding
    private lateinit var b : ActivityMainBinding

    //Fragment
    private lateinit var fragmentManager : FragmentManager
    private lateinit var fragmentTransaction : FragmentTransaction
    private lateinit var homeFragment: HomeFragment
    private lateinit var activityFragment: ActivityFragment
    private var isHome : Boolean = true
    private var isActivity : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //declare data binding
        b = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //initialize fragment
        homeFragment = HomeFragment().newInstance()
        activityFragment = ActivityFragment().newInstance()

    }

}