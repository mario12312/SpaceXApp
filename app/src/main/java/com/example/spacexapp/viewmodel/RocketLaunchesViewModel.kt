package com.example.spacexapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spacexapp.model.RocketsLaunchesResp
import com.example.spacexapp.model.RocketsLaunchesRespItem
import com.example.spacexapp.repository.RocketLaunchesRepository
import com.example.spacexapp.util.Coroutines
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
class RocketLaunchesViewModel(
    private val repository: RocketLaunchesRepository
) : ViewModel() {

    private val _rockets = MutableLiveData<ArrayList<RocketsLaunchesRespItem>>()
    val rockets: LiveData<ArrayList<RocketsLaunchesRespItem>>
        get() = _rockets

    fun getRockets() {
        viewModelScope.launch {
            Coroutines.ioThenMain(
                {
                    repository.getRockets()
                },
                {
                    _rockets.value = it
                }
            )
        }
    }
}