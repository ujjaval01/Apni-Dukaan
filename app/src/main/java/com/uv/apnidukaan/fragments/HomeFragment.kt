package com.uv.apnidukaan.fragments

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.uv.apnidukaan.R
import com.uv.apnidukaan.adapter.TransactionAdapter
import com.uv.apnidukaan.model.Transaction
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HomeFragment : Fragment() {

    private lateinit var chart: LineChart

    //for time and date
    private lateinit var tvDateTime: TextView
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        //for date and time
        tvDateTime = view.findViewById(R.id.tvDateTime)

        //for chart with dummy data
        chart = view.findViewById(R.id.profitLossChart)
        setupProfitLossChart()

        val rvTransactions: RecyclerView = view.findViewById(R.id.rvTransactions)
        rvTransactions.layoutManager = LinearLayoutManager(requireContext())

        val dummyTransactions = listOf(
            Transaction(1, "Urea", 5, 1125.0, "28 Aug 2025", "SELL"),
            Transaction(2, "Fordan", 1, 1200.0, "27 Aug 2025", "BUY"),
            Transaction(3, "Zink + DAP", 10, 5000.0, "27 Aug 2025", "SELL"),
            Transaction(4, "Super Ramban", 2, 1600.0, "26 Aug 2025", "SELL")
        )

        rvTransactions.adapter = TransactionAdapter(dummyTransactions)

        return view
    }

    private fun setupProfitLossChart() {
        // 🔹 Sample transactions: Pair(Day, Profit/Loss)
        val transactions = listOf(
            Pair(1f, 1500f),
            Pair(2f, 800f),
            Pair(3f, -200f),
            Pair(4f, 1200f),
            Pair(5f, -500f),
            Pair(6f, 2000f)
        )

        val entries = ArrayList<Entry>()
        for ((day, profit) in transactions) {
            entries.add(Entry(day, profit))
        }

        val dataSet = LineDataSet(entries, "Profit vs Loss")
        dataSet.lineWidth = 2f
        dataSet.setDrawFilled(true)
        dataSet.setDrawValues(true)

        // Color based on positive/negative profit
        val colors = ArrayList<Int>()
        for (entry in entries) {
            if (entry.y >= 0) colors.add(Color.GREEN) else colors.add(Color.RED)
        }
        dataSet.colors = colors
        dataSet.valueTextColor = Color.BLACK
        dataSet.fillColor = Color.LTGRAY

        val lineData = LineData(dataSet)
        chart.data = lineData
        chart.description.isEnabled = false
        chart.animateY(1000)
        chart.invalidate()
    }

    override fun onResume() {
        super.onResume()
        runnable = object : Runnable {
            override fun run() {
                val currentDateTime = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
                    .format(Date())
                tvDateTime.text = currentDateTime
                handler.postDelayed(this, 1000)
            }
        }
        handler.post(runnable)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }
}
