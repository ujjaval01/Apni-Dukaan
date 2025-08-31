package com.uv.apnidukaan

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.uv.apnidukaan.fragments.*

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_navigation)

        val prefs = getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val langChanged = prefs.getBoolean("Lang_Changed", false)

        // 🔹 BottomNav listener
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.bottom_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.stock -> {
                    replaceFragment(StockFragment())
                    true
                }
                R.id.bottom_favorite -> {
                    replaceFragment(FavoriteFragment())
                    true
                }
                R.id.bottom_transaction -> {
                    replaceFragment(TransactionsFragment())
                    true
                }
                R.id.bottom_setting -> {
                    replaceFragment(SettingFragment())
                    true
                }
                else -> false
            }
        }

        if (savedInstanceState == null) {
            if (langChanged) {
                // ✅ Agar language change hui thi → ek baar Settings open karo
                bottomNavigationView.selectedItemId = R.id.bottom_setting
                replaceFragment(SettingFragment())
                prefs.edit().putBoolean("Lang_Changed", false).apply()
            } else {
                // ✅ Normal case → hamesha Home
                bottomNavigationView.selectedItemId = R.id.bottom_home
                replaceFragment(HomeFragment())
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment)
            .commit()
    }
}
