package com.example.spacexapp.ui.adapter

import com.example.spacexapp.base.BaseViewHolder
import com.example.spacexapp.databinding.RocketlaunchesItemBinding
import com.example.spacexapp.model.RocketsLaunchesResp
import com.example.spacexapp.model.RocketsLaunchesRespItem
import com.example.spacexapp.ui.listener.OnRocketSelectedListener
import javax.inject.Inject

class RocketItemsViewHolder(val binding: RocketlaunchesItemBinding,
                                                val listener: OnRocketSelectedListener) :
    BaseViewHolder<ArrayList<RocketsLaunchesRespItem>>(binding.getRoot()) {

    override fun bind(data: ArrayList<RocketsLaunchesRespItem>) {
        binding.executePendingBindings()
    }

}