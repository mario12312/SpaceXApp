package com.example.spacexapp.util

import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import com.example.spacexapp.R


object AppUtil {

    fun getNavOptions(): NavOptions =
        navOptions {
            anim {
                enter = R.anim.enter
                exit = R.anim.exit
            }
        }
}

