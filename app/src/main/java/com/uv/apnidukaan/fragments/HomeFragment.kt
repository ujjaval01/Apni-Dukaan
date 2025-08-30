package com.uv.apnidukaan.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uv.apnidukaan.R
import com.uv.apnidukaan.adapter.TransactionAdapter
import com.uv.apnidukaan.model.Transaction

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

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
}
