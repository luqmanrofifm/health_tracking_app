package com.example.healthtracking.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.healthtracking.R
import com.example.healthtracking.databinding.FragmentHomeBinding
import com.example.healthtracking.viewmodel.WaterViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var b : FragmentHomeBinding
    private val waterViewModel : WaterViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        b.lifecycleOwner = this
        b.tvWater.text = waterViewModel.water.value.toString()

        b.btAddActivities.setOnClickListener {
            val intent = Intent(activity, AddActivity::class.java)
            startActivity(intent)
        }
        return b.root
    }

    fun newInstance() : HomeFragment {
        return HomeFragment()
    }

}