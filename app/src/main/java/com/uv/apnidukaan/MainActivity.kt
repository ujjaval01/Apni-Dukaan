package com.uv.apnidukaan

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.uv.apnidukaan.fragments.*

class MainActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase?.let { com.uv.apnidukaan.utils.LocaleHelper.loadLocale(it) })
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        val prefs = getSharedPreferences("Settings", MODE_PRIVATE)
        val savedTheme = prefs.getString("My_Theme", "light")

        if (savedTheme == "dark") {
            setTheme(R.style.Theme_ApniDukaan_Dark)
        } else {
            setTheme(R.style.Theme_ApniDukaan_Light)
        }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.bottom_home -> { replaceFragment(HomeFragment()); true }
                R.id.stock -> { replaceFragment(StockFragment()); true }
                R.id.bottom_favorite -> { replaceFragment(FavoriteFragment()); true }
                R.id.bottom_transaction -> { replaceFragment(TransactionsFragment()); true }
                R.id.bottom_setting -> { replaceFragment(SettingFragment()); true }
                else -> false
            }
        }

        if (savedInstanceState == null) {
            bottomNavigationView.selectedItemId = R.id.bottom_home
            replaceFragment(HomeFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment)
            .commit()
    }
}
