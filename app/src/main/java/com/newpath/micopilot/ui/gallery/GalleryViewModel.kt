package com.newpath.micopilot.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.newpath.micopilot.network.TafApi
import com.newpath.micopilot.network.TafDataList
import com.newpath.micopilot.network.TafDataModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.await

class GalleryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }

    private val _property = MutableLiveData<List<TafDataModel>>()


    val text: LiveData<String> = _text

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.Main )


    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getTafData()
    }



    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    private fun getTafData() {
        coroutineScope.launch {
            var getPropertiesDeferred = TafApi.retrofitService.getProperties()
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