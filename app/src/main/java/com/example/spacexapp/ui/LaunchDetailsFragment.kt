package com.example.spacexapp.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.spacexapp.R
import com.example.spacexapp.databinding.LaunchDetailsFragmentBinding
import com.example.spacexapp.exceptions.checkForConnectivity
import com.example.spacexapp.exceptions.showErrorSnackBar
import com.example.spacexapp.network.getRocketDetailsApi
import com.example.spacexapp.network.getRocketsApi
import com.example.spacexapp.repository.RocketLaunchDetailsRepository
import com.example.spacexapp.repository.RocketLaunchesRepository
import com.example.spacexapp.viewmodel.LaunchDetailsViewModel
import com.example.spacexapp.viewmodel.RocketLaunchesViewModel
import com.example.spacexapp.viewmodel.factory.RocketLaunchDetailsViewModelFactory
import com.example.spacexapp.viewmodel.factory.RocketLaunchesViewModelFactory

class LaunchDetailsFragment: Fragment(R.layout.launch_details_fragment) {

    private lateinit var binding: LaunchDetailsFragmentBinding
    private lateinit var viewModel: LaunchDetailsViewModel
    private val args: LaunchDetailsFragmentArgs? by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.launch_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LaunchDetailsFragmentBinding.bind(view)


        binding.sharebtn.setOnClickListener {
            showErrorSnackBar("Coming soon!")
        }
        binding.closebtn.setOnClickListener {
            findNavController().navigate(R.id.rocketsLaunchesFragment)
        }

    }

    fun openNewTabWindow(urls: String, context : Context) {
        val uris = Uri.parse(urls)
        val intents = Intent(Intent.ACTION_VIEW, uris)
        val b = Bundle()
        b.putBoolean("new_window", true)
        intents.putExtras(b)
        context.startActivity(intents)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val api = getRocketDetailsApi()
        val repository = RocketLaunchDetailsRepository(api)

        @Suppress("DEPRECATION")
        viewModel = ViewModelProviders.of(this, RocketLaunchDetailsViewModelFactory(repository))
            .get(LaunchDetailsViewModel::class.java)

        if(checkForConnectivity(requireContext())){
            viewModel.getRocketDetails(args?.rocketId.toString())
            getRocketDetails()
        }else{
            showErrorSnackBar("Please check your internet connection.")
        }
    }

    private fun getRocketDetails() {
        viewModel.rocketDetails.observe(viewLifecycleOwner, Observer { rocketdetails ->
            if (rocketdetails != null){
                for(rocket in rocketdetails){
                    if(rocket.active){
                        binding.txttitle.text = rocket.name
                        binding.txtnumber.text = rocket.id
                        binding.txtdesc.text = rocket.description

                        Glide.with(this).load(rocket.flickrImages[0]).into(binding.imgBg)

                        binding.readmorebtn.setOnClickListener {
                            openNewTabWindow(rocket.wikipedia,requireContext())
                        }
                    }
                }
            }
        })
    }

}