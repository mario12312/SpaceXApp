package com.example.spacexapp.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.spacexapp.R
import com.example.spacexapp.databinding.LaunchDetailsFragmentBinding
import com.example.spacexapp.exceptions.showErrorSnackBar
import com.example.spacexapp.viewmodel.LaunchDetailsViewModel

class LaunchDetailsFragment: Fragment(R.layout.launch_details_fragment) {

    companion object {
        fun newInstance() = LaunchDetailsFragment()
    }
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

        binding.txttitle.text = args!!.rocketTitle
        binding.txtnumber.text = args!!.rocketNumber
        binding.txtdate.text = args!!.rocketDate
        binding.txtdesc.text = args!!.rocketDetails

        binding.readmorebtn.setOnClickListener {
            args!!.readMoreUrl?.let { it1 -> openNewTabWindow(it1, requireContext()) }
        }

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

    }

}