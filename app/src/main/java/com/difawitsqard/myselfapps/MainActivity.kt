package com.difawitsqard.myselfapps

// Created : 26/05/24
// NIM     : 10121916
// Nama    : Difa Witsqa RD
// Kelas   : IF9K

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.difawitsqard.myselfapps.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.selectedItemId = R.id.Home


        replaceFragment(Home())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.Home -> replaceFragment(Home())
                R.id.Profile -> replaceFragment(Profile())
                R.id.Gallery -> replaceFragment(Gallery())
                R.id.MusicVideo -> replaceFragment(MusicVideo())
                R.id.DailyAcitivty -> replaceFragment(DailyActivity())
                else -> {

                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}