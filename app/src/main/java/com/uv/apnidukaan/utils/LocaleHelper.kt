package com.uv.apnidukaan.utils

import android.content.Context
import android.content.res.Configuration
import java.util.*

object LocaleHelper {

    fun setLocale(context: Context, lang: String): Context {
        val locale = Locale(lang)
        Locale.setDefault(locale)

        val config = Configuration()
        config.setLocale(locale)
        config.setLayoutDirection(locale)

        // Save preference
        val prefs = context.getSharedPreferences("Settings", Context.MODE_PRIVATE)
        prefs.edit().putString("My_Lang", lang).apply()

        return context.createConfigurationContext(config)
    }

    fun loadLocale(context: Context): Context {
        val prefs = context.getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val lang = prefs.getString("My_Lang", "en") ?: "en"
        return setLocale(context, lang)
    }
}
