package com.example.spacexapp.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.spacexapp.repository.RocketLaunchDetailsRepository
import com.example.spacexapp.repository.RocketLaunchesRepository
import com.example.spacexapp.viewmodel.LaunchDetailsViewModel
import com.example.spacexapp.viewmodel.RocketLaunchesViewModel

class RocketLaunchDetailsViewModelFactory (
    private val repository: RocketLaunchDetailsRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LaunchDetailsViewModel(repository) as T
    }

}