package com.example.healthtracking

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import java.util.*
import javax.xml.datatype.DatatypeConstants.DAYS


class ActivityFragment : Fragment() {

    private lateinit var barChart: BarChart
    private val barEntriesArrayList = ArrayList<BarEntry>()
    private lateinit var barData: BarData
    private lateinit var barDataSet: BarDataSet

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
    }

    private fun getBarEntries() {
        //barEntriesArrayList = ArrayList()

        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.

        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        barEntriesArrayList.add(BarEntry(1f, 4.toFloat()))
        barEntriesArrayList.add(BarEntry(2f, 6.toFloat()))
        barEntriesArrayList.add(BarEntry(3f, 8.toFloat()))
        barEntriesArrayList.add(BarEntry(4f, 2.toFloat()))
        barEntriesArrayList.add(BarEntry(5f, 4.toFloat()))
        barEntriesArrayList.add(BarEntry(6f, 1.toFloat()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root = inflater.inflate(R.layout.fragment_activity, container, false)
        barChart = root.findViewById(R.id.barchart_activity)
        // calling method to get bar entries.
        // calling method to get bar entries.
        getBarEntries()

        // creating a new bar data set.
        val labels = ArrayList<String>()
        labels.add("18-Jan")
        labels.add("19-Jan")
        labels.add("20-Jan")
        labels.add("21-Jan")
        labels.add("22-Jan")
        labels.add("23-Jan")
        // creating a new bar data set.
        barDataSet = BarDataSet(barEntriesArrayList, null)
        // creating a new bar data and
        // passing our bar data set.

        // creating a new bar data and
        // passing our bar data set.
        barData = BarData(barDataSet)

        // below line is to set data
        // to our bar chart.

        // below line is to set data
        // to our bar chart.
        barChart.data = barData

        // adding color to our bar data set.

        // adding color to our bar data set.
        barDataSet.color = resources.getColor(R.color.purpleMain)

        // setting text color.

        // setting text color.
        barDataSet.setValueTextColor(Color.BLACK)

        // setting text size

        // setting text size
        barDataSet.setValueTextSize(16f)
        barChart.description.isEnabled = false

        //barChart.xAxis.isEnabled = false
        barChart.axisLeft.isEnabled = false
        barChart.axisRight.isEnabled = false
        barChart.xAxis.position = XAxis.XAxisPosition.BOTTOM

        barChart.xAxis.valueFormatter = MyValueFormatter(labels)


        return root//inflater.inflate(R.layout.fragment_activity, container, false)
    }

    fun newInstance() : ActivityFragment {
        return ActivityFragment()
    }
}

class MyValueFormatter(private val xValsDateLabel: ArrayList<String>) : ValueFormatter() {

    override fun getFormattedValue(value: Float): String {
        return value.toString()
    }

    /*
    override fun getAxisLabel(value: Float, axis: AxisBase): String {
        if (value.toInt() >= 0 && value.toInt() <= xValsDateLabel.size - 1) {
            return xValsDateLabel[value.toInt()]
        } else {
            return ("").toString()
        }
    }*/

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return xValsDateLabel.getOrNull(value.toInt()) ?: value.toString()
    }
}