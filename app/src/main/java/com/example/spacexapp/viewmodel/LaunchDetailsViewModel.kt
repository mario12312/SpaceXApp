package com.example.spacexapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacexapp.model.RocketsLaunchesRespItem
import com.example.spacexapp.model.rocketdetails.RocketLaunchDetailsRespItem
import com.example.spacexapp.repository.RocketLaunchDetailsRepository
import com.example.spacexapp.util.Coroutines
import kotlinx.coroutines.launch

class LaunchDetailsViewModel(
    private val repository: RocketLaunchDetailsRepository
) : ViewModel() {

    private val _rocketDetails = MutableLiveData<ArrayList<RocketLaunchDetailsRespItem>>()
    val rocketDetails: LiveData<ArrayList<RocketLaunchDetailsRespItem>>
        get() = _rocketDetails

    fun getRocketDetails(id: String) {
        viewModelScope.launch {
            Coroutines.ioThenMain(
                {
                    repository.getRocketDetails(id)
                },
                {
                    _rocketDetails.value = it
                }
            )
        }
    }
}