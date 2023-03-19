package com.ibrahim.mvvm_travelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.ibrahim.mvvm_travelapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val navController = findNavController(R.id.host_fragment)
        NavigationUI.setupWithNavController(activityMainBinding.btmNav,navController)

        activityMainBinding.btmNav.itemIconTintList = null
    }
}