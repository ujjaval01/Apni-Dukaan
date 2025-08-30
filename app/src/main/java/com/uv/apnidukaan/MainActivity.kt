package com.uv.apnidukaan

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.uv.apnidukaan.fragments.FavoriteFragment
import com.uv.apnidukaan.fragments.HomeFragment
import com.uv.apnidukaan.fragments.ManageAccountFragment
import com.uv.apnidukaan.fragments.NotificationFragment
import com.uv.apnidukaan.fragments.SettingFragment

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.bottom_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.bottom_manage_account -> {
                    replaceFragment(ManageAccountFragment())
                    true
                }
                R.id.bottom_favorite -> {
                    replaceFragment(FavoriteFragment())
                    true
                }
                R.id.bottom_notification -> {
                    replaceFragment(NotificationFragment())
                    true
                }
                R.id.bottom_setting -> {
                    replaceFragment(SettingFragment())
                    true
                }
                else -> false
            }

        }
        replaceFragment(HomeFragment())
    }
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment)
            .commit()
    }

}