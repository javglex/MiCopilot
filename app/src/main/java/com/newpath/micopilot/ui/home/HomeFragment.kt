package com.newpath.micopilot.ui.home

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.newpath.micopilot.MainActivity
import com.newpath.micopilot.R
import com.newpath.micopilot.databinding.FragmentHomeBinding
import timber.log.Timber
import timber.log.Timber.d
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView( inflater: LayoutInflater,
                               container: ViewGroup?,
                               savedInstanceState: Bundle?): View? {

        homeViewModel = ViewModelProvider(activity as MainActivity).get(HomeViewModel::class.java)

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )

        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            Timber.d("Observed texthome")

            binding.textHome.text = it
        })

        homeViewModel.location.observe(viewLifecycleOwner, Observer {
            Timber.d("Observed location")
            if(it!=null)
                binding.textGpsCoord.text = "acc: " + it.accuracy + " loc: " + it.latitude + " ,"+it.longitude
        })

        homeViewModel.orientation.observe(viewLifecycleOwner, Observer {
            Timber.d("Observed orientation")
            if(it!=null)
                binding.textOrientation.text = it.contentToString()
        })

        homeViewModel.searcharea.observe(viewLifecycleOwner, Observer {
            Timber.d("Observed search area")
            if(it!=null)
                binding.textSearchArea.text = it.toString()
        })

        binding.btnRequestOrientation.setOnClickListener {
            homeViewModel.requestOrientation()
        }

        return binding.root

    }
}
