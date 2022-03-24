package com.example.spacexapp.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.spacexapp.repository.RocketLaunchesRepository
import com.example.spacexapp.viewmodel.RocketLaunchesViewModel

class RocketLaunchesViewModelFactory(
    private val repository: RocketLaunchesRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return RocketLaunchesViewModel(repository) as T
    }

}