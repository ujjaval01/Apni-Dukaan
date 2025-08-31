package com.uv.apnidukaan.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import com.uv.apnidukaan.R
import com.uv.apnidukaan.fragments.subFragments.HelpSupportFragment

class SettingFragment : Fragment() {

    private lateinit var option_help: LinearLayout


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_setting, container, false)

        option_help = view.findViewById(R.id.option_help)
        option_help.setOnClickListener {
            val helpSupportFragment = HelpSupportFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.frame_container, helpSupportFragment)
                .addToBackStack(null)
                .commit()
        }


        return view
    }

}