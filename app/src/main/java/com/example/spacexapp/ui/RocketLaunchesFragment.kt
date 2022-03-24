package com.example.spacexapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.spacexapp.viewmodel.RocketLaunchesViewModel

import android.graphics.*

import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spacexapp.R
import com.example.spacexapp.databinding.RocketLaunchesFragmentBinding
import com.example.spacexapp.exceptions.NoConnectivityException
import com.example.spacexapp.exceptions.checkForConnectivity
import com.example.spacexapp.exceptions.showErrorSnackBar
import com.example.spacexapp.model.RocketsLaunchesResp
import com.example.spacexapp.model.RocketsLaunchesRespItem
import com.example.spacexapp.network.getRocketsApi
import com.example.spacexapp.repository.RocketLaunchesRepository
import com.example.spacexapp.ui.adapter.RocketLaunchesAdapter
import com.example.spacexapp.ui.listener.OnRocketSelectedListener
import com.example.spacexapp.util.AppUtil
import com.example.spacexapp.viewmodel.factory.RocketLaunchesViewModelFactory
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RocketLaunchesFragment: Fragment(),
OnRocketSelectedListener{

    private lateinit var viewModel: RocketLaunchesViewModel
    private lateinit var binding: RocketLaunchesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.rocket_launches_fragment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val api = getRocketsApi()
        val repository = RocketLaunchesRepository(api)

        @Suppress("DEPRECATION")
        viewModel = ViewModelProviders.of(this, RocketLaunchesViewModelFactory(repository))
            .get(RocketLaunchesViewModel::class.java)

//        val viewModel: RocketLaunchesViewModel by viewModels { factory }
        if(checkForConnectivity(requireContext())){
            viewModel.getRockets()
            getRockets()
        }else{
            showErrorSnackBar("Please check your internet connection.")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = RocketLaunchesFragmentBinding.bind(view)
        try {
//            val viewModel: RocketLaunchesViewModel = ViewModelProvider(ViewModelStoreOwner { factory },this)
//            val viewModel = ViewModelProvider(this).get(RocketLaunchesViewModel::class.java)

            val mimageView: ImageView = binding.imgview
            val mbitmap = (resources.getDrawable(com.example.spacexapp.R.drawable.download_scaled) as BitmapDrawable).bitmap
            val imageRounded = Bitmap.createBitmap(mbitmap.width, mbitmap.height, mbitmap.config)
            val canvas = Canvas(imageRounded)
            val mpaint = Paint()
            mpaint.setAntiAlias(true)
            mpaint.setShader(BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP))
            canvas.drawRoundRect(
                RectF(0F, 0F, mbitmap.width.toFloat(), mbitmap.height.toFloat()),
                100F,
                100F,
                mpaint
            ) // Round Image Corner 100 100 100 100

            mimageView.setImageBitmap(imageRounded)

        }catch (ex: Exception){
            Log.i("RocketLaunchesFragment","error: " + ex.message)
        }

//        val rocketLaunchesAdapter: RocketLaunchesAdapter = RocketLaunchesAdapter(list)
//        recycler.adapter = rocketLaunchesAdapter
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getRockets(){
        val recycler = binding.rvRockets
        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val filteredRockets = ArrayList<RocketsLaunchesRespItem>()
        viewModel.rockets.observe(viewLifecycleOwner, Observer { rockets ->
            if(rockets != null){
                val formatter = DateTimeFormatter.ofPattern("yyyy")
                val current = LocalDateTime.now()
                val currentYear = current.format(formatter).toInt()
                val arrYears: ArrayList<String> = ArrayList()
                arrYears.add(currentYear.toString())
                arrYears.add((currentYear-1).toString())
                arrYears.add((currentYear-2).toString())
                for(rocket in rockets){
                    val rocketDate = rocket.dateUtc
                    val launchYear = rocketDate.split("-")[0]
                    if(arrYears.contains(launchYear)){
                        if(rocket.success || (rocket.success == null && rocket.upcoming)){
                            filteredRockets.add(rocket)
                        }
                    }
                }
                binding.progressBar.visibility = View.GONE
                recycler.adapter = RocketLaunchesAdapter(filteredRockets,this)
                recycler.setHasFixedSize(true)
            }
        })
    }

    override fun onRocketLaunchSelected(rocketsLaunchesResponseItem: RocketsLaunchesRespItem) {
//        Toast.makeText(context, "rocket number: " + rocketsLaunchesResponseItem.name, Toast.LENGTH_SHORT).show()
        findNavController().navigate(
            R.id.LaunchDetailsFragment,
            bundleOf(
                "RocketId" to rocketsLaunchesResponseItem.id
            ),
            AppUtil.getNavOptions()
        )
    }

}