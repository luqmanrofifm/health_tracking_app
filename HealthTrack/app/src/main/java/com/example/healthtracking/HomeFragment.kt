package com.example.healthtracking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.healthtracking.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var b : FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        b = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        b.lifecycleOwner = this
        return b.root
    }

    fun newInstance() : HomeFragment {
        return HomeFragment()
    }
}