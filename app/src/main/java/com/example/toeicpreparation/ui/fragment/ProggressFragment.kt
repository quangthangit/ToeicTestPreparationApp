package com.example.toeicpreparation.ui.fragment

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.fragment.app.Fragment
import com.example.toeicpreparation.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class ProggressFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_proggress, container, false)
        val barChart = view.findViewById<BarChart>(R.id.idBarChart)

        setupBarChart(barChart)

        return view
    }

    private fun setupBarChart(barChart: BarChart) {
        val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
        val hours = listOf(2f, 3.5f, 1f, 4f, 3f, 5f, 2.5f)
        val entries = hours.mapIndexed { index, value -> BarEntry(index.toFloat(), value) }

        val dataSet = BarDataSet(entries, "Giờ hoạt động (giờ)").apply {
            color = "#009BF3".toColorInt()
            valueTextSize = 8f
            valueTextColor = Color.BLACK
        }

        val barData = BarData(dataSet).apply {
            barWidth = 0.8f

        }

        barChart.apply {
            data = barData
            setFitBars(true)
            description.isEnabled = false
            legend.isEnabled = false
            animateY(1000)
            invalidate()
            setExtraOffsets(10f, 10f, 20f, 10f)
            setupXAxis(xAxis, days)
            axisLeft.isEnabled = false
            axisRight.isEnabled = false
        }
    }

    private fun setupXAxis(xAxis: XAxis, labels: List<String>) {
        xAxis.apply {
            valueFormatter = IndexAxisValueFormatter(labels)
            position = XAxis.XAxisPosition.BOTTOM
            setDrawGridLines(false)
            granularity = 1f
            labelCount = labels.size
            textSize = 12f
            yOffset = 10f
            textColor = "#82898F".toColorInt()
            Typeface.MONOSPACE
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProggressFragment().apply {
                arguments = Bundle().apply {
                    putString("param1", param1)
                    putString("param2", param2)
                }
            }
    }
}
