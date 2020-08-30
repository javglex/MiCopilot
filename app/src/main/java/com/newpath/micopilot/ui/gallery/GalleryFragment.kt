package com.newpath.micopilot.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.newpath.micopilot.R
import com.newpath.micopilot.adapter.AirportAdapter
import com.newpath.micopilot.databinding.FragmentGalleryBinding
import com.newpath.micopilot.models.GPSPoint
import com.newpath.micopilot.utils.SharedPrefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentGalleryBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_gallery, container, false)

        galleryViewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)

        binding.lifecycleOwner = this
        binding.galleryFragmentViewModel = galleryViewModel

        val adapter = AirportAdapter()
        binding.rvAirportList.adapter = adapter

        val searchArea: List<GPSPoint>? = SharedPrefs.getInstance(context).searchArea
        if (!searchArea.isNullOrEmpty())
            galleryViewModel.getTafData(searchArea[0].X, searchArea[0].Y, searchArea[1].X, searchArea[1].Y, 15.0)

        galleryViewModel._property.observe(viewLifecycleOwner, Observer {
            it?.let{

                adapter.airportList = it

            }
        })


        
        return binding.root
    }



}
