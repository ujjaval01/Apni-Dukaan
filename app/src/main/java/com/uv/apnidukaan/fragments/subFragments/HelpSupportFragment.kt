package com.uv.apnidukaan.fragments.subFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.uv.apnidukaan.R

class HelpSupportFragment : Fragment() {

    private lateinit var backBtn: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_help_support, container, false)

        backBtn = view.findViewById(R.id.backBtn)
        backBtn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return view
    }
}