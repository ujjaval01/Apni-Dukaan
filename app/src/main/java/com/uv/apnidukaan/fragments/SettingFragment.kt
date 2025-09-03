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
    private lateinit var option_theme: LinearLayout
    private lateinit var option_notifications: LinearLayout
    private lateinit var option_account: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_setting, container, false)

        option_help = view.findViewById(R.id.option_help)
        option_security = view.findViewById(R.id.option_security)
        option_backup = view.findViewById(R.id.option_backup)
        option_lang = view.findViewById(R.id.option_lang)
        option_theme = view.findViewById(R.id.option_theme)

        // ✅ Kotlin style click listener
        option_lang.setOnClickListener {
            showLanguageDialog()
        }
        // ✅ Kotlin style click listener
        option_theme.setOnClickListener {
            showThemeDialog()
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

    // 🔹 Show Lang Dialog
    private fun showLanguageDialog() {
        val languages = arrayOf("English", "हिन्दी")

        val prefs = requireActivity()
            .getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val savedLang = prefs.getString("My_Lang", "en")
        var checkedItem = if (savedLang == "hi") 1 else 0

        AlertDialog.Builder(requireContext())
            .setTitle("Choose Language")
            .setSingleChoiceItems(languages, checkedItem) { dialog, which ->
                val langCode = if (which == 0) "en" else "hi"
                com.uv.apnidukaan.utils.LocaleHelper.setLocale(requireContext(), langCode)

                requireActivity().recreate() // refresh UI
                dialog.dismiss()
            }
            .create()
            .show()
    }

    // show theme dialog
    private fun showThemeDialog() {
        val themes = arrayOf("Light", "Dark")
        val prefs = requireActivity()
            .getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val savedTheme = prefs.getString("My_Theme", "light")

        val checkedItem = if (savedTheme == "dark") 1 else 0
        AlertDialog.Builder(requireContext())
            .setTitle("Choose Theme")
            .setSingleChoiceItems(themes, checkedItem) { dialog, which ->
                val theme = if (which == 0) "light" else "dark"
                prefs.edit {
                    putString("My_Theme", theme)
                }
                requireActivity().recreate() // refresh UI
                dialog.dismiss()
            }
            .create()
            .show()
    }

}
