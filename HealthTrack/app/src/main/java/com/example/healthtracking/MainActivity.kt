package com.example.healthtracking

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.ViewPager
import com.example.healthtracking.adapter.ViewPagerAdapter
import com.example.healthtracking.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    //Data Binding
    private lateinit var b : ActivityMainBinding

    //Fragment
    private lateinit var fragmentManager : FragmentManager
    private lateinit var fragmentTransaction : FragmentTransaction
    private lateinit var homeFragment: HomeFragment
    private lateinit var activityFragment: ActivityFragment
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private var isHome : Boolean = true
    private var isActivity : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //declare data binding
        b = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //initialize fragment
        homeFragment = HomeFragment().newInstance()
        activityFragment = ActivityFragment().newInstance()

        //attaching ViewPager
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewpager)
        setupViewPager(viewPager)
        tabLayout.setupWithViewPager(viewPager)

        //set OnClickListener
        b.btHome.setOnClickListener {
            viewPager.currentItem = 0
        }
        b.btAdd.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment(),"Home")
        adapter.addFragment(ActivityFragment(), "Activity")
        viewPager.adapter = adapter
    }

}