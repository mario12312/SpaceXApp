package com.example.spacexapp.ui.listener

import com.example.spacexapp.model.RocketsLaunchesRespItem

interface OnRocketSelectedListener {
    fun onRocketLaunchSelected(rocketsLaunchesResponseItem: RocketsLaunchesRespItem)
}