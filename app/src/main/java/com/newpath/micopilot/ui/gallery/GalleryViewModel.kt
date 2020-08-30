package com.newpath.micopilot.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.newpath.micopilot.models.GPSPoint
import com.newpath.micopilot.network.TafApi
import com.newpath.micopilot.network.TafDataList
import com.newpath.micopilot.network.TafDataModel
import com.newpath.micopilot.utils.SharedPrefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.await

class GalleryViewModel : ViewModel() {


    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }

    val _property = MutableLiveData<List<TafDataModel>>()


    val text: LiveData<String> = _text

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main )


    /**
     *
     */
    init {

    }

    fun getTafData(x1: Double, y1: Double, x2:Double, y2: Double, width: Double){
        fetchTafData(x1, y1, x2, y2, width)
    }



    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    private fun fetchTafData(x1: Double, y1: Double, x2:Double, y2: Double, width: Double) {
        coroutineScope.launch {

            val query: String = "$width;$y1,$x1;$y2,$x2"

            var getPropertiesDeferred = TafApi.retrofitService.getProperties(query)
            try {
                var listResult: TafDataList = getPropertiesDeferred.await()
                _text.value =
                    "Success: ${listResult} taf info retrieved"

                if (!listResult.TAFS.isNullOrEmpty()) {
                    _property.value = listResult.TAFS
                }

            } catch (e: Exception) {
                _text.value = "Failure: ${e.message}"
                e.printStackTrace()

            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}