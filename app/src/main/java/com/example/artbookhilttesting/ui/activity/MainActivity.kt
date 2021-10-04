package com.example.artbookhilttesting.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.artbookhilttesting.R
import com.example.artbookhilttesting.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController  : NavController
    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = DataBindingUtil.setContentView(this , R.layout.activity_main)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController   = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.artsFragment,
            R.id.addArtDetailsFragment,
            R.id.artsFragment,
            R.id.imgaeApiFragment
        ))
        setupActionBarWithNavController(navController,appBarConfiguration)
    }
}