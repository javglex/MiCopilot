package com.newpath.micopilot.ui.home

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.newpath.micopilot.models.GPSPoint
import com.newpath.micopilot.utils.AreaData
import timber.log.Timber

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>()
    val text: LiveData<String>
        get() = _text

    private val _location = MutableLiveData<Location>()
    val location: LiveData<Location>
        get() = _location
    // The String version of the location
    val locationString = Transformations.map(location) { l ->
        "acc: " + l?.accuracy + " loc: " + l?.latitude + " ,"+l?.longitude
    }

    private val _orientation = MutableLiveData<FloatArray>()
    val orientation: LiveData<FloatArray>
        get() = _orientation

    // The String version of the orientation
    val orientationString = Transformations.map(orientation) { o ->
        o.contentToString()
    }

    private val _searcharea = MutableLiveData<List<GPSPoint>>()
    val searcharea: LiveData<List<GPSPoint>>
        get() = _searcharea

    private val _orientationRequested = MutableLiveData<Boolean>()
    val orientationRequested: LiveData<Boolean>
        get() = _orientationRequested

    init
    {

        _text.value = "This is home Fragment"

    }

    fun setLocation(location: Location?){
        Timber.d("Set location" + location.toString())
        _location.value = location
    }

    fun requestOrientation(){
        _orientationRequested.value = true
        _orientationRequested.value = false
    }

    fun setOrientation(rotation: FloatArray){
        _orientation.value = rotation
        setSearchArea()
    }

    fun setSearchArea(){

        if (_location.value==null) {
            Timber.w("No location value available")
            return
        }

        var origin: GPSPoint = GPSPoint(_location.value!!.latitude,_location.value!!.longitude)
        var searchArea: AreaData = AreaData(origin)

        if (_orientation.value?.get(0)!=null) {
            var rotation: Double = _orientation.value!![0].toDouble();
            var areaPoints: List<GPSPoint> = searchArea.generateMapArea(rotation)
            _searcharea.value = areaPoints
            return
        }

        Timber.w("No location orientation available")



    }



}