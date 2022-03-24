package com.example.spacexapp.ui.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.spacexapp.R
import com.example.spacexapp.base.BaseViewHolder
import com.example.spacexapp.databinding.RocketlaunchesItemBinding
import com.example.spacexapp.model.RocketsLaunchesResp
import com.example.spacexapp.model.RocketsLaunchesRespItem
import com.example.spacexapp.ui.listener.OnRocketSelectedListener
import javax.inject.Inject

class RocketLaunchesAdapter(
    val items: ArrayList<RocketsLaunchesRespItem>,
    private val listener: OnRocketSelectedListener
)
    : RecyclerView.Adapter<BaseViewHolder<ArrayList<RocketsLaunchesRespItem>>>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ArrayList<RocketsLaunchesRespItem>> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: RocketlaunchesItemBinding =
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.rocketlaunches_item,
                parent,
                false
            )

        return RocketItemsViewHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(
        holder: BaseViewHolder<ArrayList<RocketsLaunchesRespItem>>,
        position: Int
    ) {
        holder.bind(items)
        if (holder is RocketItemsViewHolder) {
            holder.binding.titletxt.text = items.get(position).flightNumber.toString()
            val flightDate = items.get(position).dateUtc
            holder.binding.subtitletxt.text = flightDate
            holder.binding.bottomtxt.text = items.get(position).name

            holder.itemView.setOnClickListener {
                listener.onRocketLaunchSelected(items.get(position))
            }
        }
    }
}