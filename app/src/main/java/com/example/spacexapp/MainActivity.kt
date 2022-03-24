package com.example.spacexapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.example.spacexapp.util.AppUtil
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentsView) as NavHostFragment? ?: return
        // Set up Action Bar
        navController = host.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            R.id.rocketsLaunchesFragment
        }
        appBarConfiguration = AppBarConfiguration(navController.graph)
    }

    override fun onBackPressed() {
        if(navController.currentDestination?.id == R.id.LaunchDetailsFragment){
            navController.navigate(R.id.rocketsLaunchesFragment)
        }else{
            super.onBackPressed()
        }
    }
}