package com.uv.apnidukaan.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.uv.apnidukaan.R
import com.uv.apnidukaan.fragments.subFragments.HelpSupportFragment
import java.util.Locale
import androidx.core.content.edit

class SettingFragment : Fragment() {

    private lateinit var option_help: LinearLayout
    private lateinit var option_security: LinearLayout
    private lateinit var option_backup: LinearLayout
    private lateinit var option_lang: LinearLayout
    private lateinit var option_notifications: LinearLayout
    private lateinit var option_account: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 🔹 Load saved language
        loadLocale()

        val view = inflater.inflate(R.layout.fragment_setting, container, false)

        option_help = view.findViewById(R.id.option_help)
        option_security = view.findViewById(R.id.option_security)
        option_backup = view.findViewById(R.id.option_backup)
        option_lang = view.findViewById(R.id.option_lang)

        // ✅ Kotlin style click listener
        option_lang.setOnClickListener {
            showLanguageDialog()
        }

        option_help.setOnClickListener {
            val helpSupportFragment = HelpSupportFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.frame_container, helpSupportFragment)
                .addToBackStack(null)
                .commit()
        }

        return view
    }

    // 🔹 Show Dialog
    private fun showLanguageDialog() {
        val languages = arrayOf("English", "हिन्दी")

        val prefs = requireActivity()
            .getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val savedLang = prefs.getString("My_Lang", "en")
        var checkedItem = if (savedLang == "hi") 1 else 0

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Choose Language")
            .setSingleChoiceItems(languages, checkedItem) { dialog, which ->
                if (which == 0) {
                    setLocale("en")
                } else if (which == 1) {
                    setLocale("hi")
                }

                // ❌ Ye flag ab zaroori nahi
                // prefs.edit().putBoolean("Lang_Changed", true).apply()

                requireActivity().recreate()
                dialog.dismiss()
            }
        builder.create().show()
    }



    // 🔹 Set locale
    private fun setLocale(lang: String) {
        val locale = Locale(lang)
        Locale.setDefault(locale)

        val config = Configuration()
        config.setLocale(locale)

        requireContext().resources.updateConfiguration(
            config, requireContext().resources.displayMetrics
        )

        // Save in SharedPreferences
        requireActivity()
            .getSharedPreferences("Settings", Context.MODE_PRIVATE)
            .edit {
                putString("My_Lang", lang)
            }
    }

    // 🔹 Load saved language
    private fun loadLocale() {
        val prefs = requireActivity()
            .getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val language = prefs.getString("My_Lang", "en") ?: "en"
        setLocale(language)
    }
    // language change ke sath selected nav item bhi save kar
    private fun saveLastSelectedNav(navId: Int) {
        requireActivity()
            .getSharedPreferences("Settings", Context.MODE_PRIVATE)
            .edit {
                putInt("Last_Nav", navId)
            }
    }

}
