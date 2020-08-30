package com.newpath.micopilot.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.newpath.micopilot.models.GPSPoint
import java.lang.reflect.Type


class SharedPrefs(context: Context?) {

    private val PREFS_FILENAME = "com.newpath.micopilot.utils.prefs"
    private val SEARCHAREA = "search_area"
    private val prefs: SharedPreferences = context!!.getSharedPreferences(PREFS_FILENAME, 0)

    companion object {
        @Volatile
        private var INSTANCE: SharedPrefs? = null

        @Synchronized
        fun getInstance(context: Context?): SharedPrefs = INSTANCE ?: SharedPrefs(context).also { INSTANCE = it }
    }



    var searchArea: List<GPSPoint>?
        get() {
            val pointsJSON = prefs.getString(SEARCHAREA, "")
            val gson = Gson()
            val type: Type = object : TypeToken<List<GPSPoint?>?>() {}.type
            val searchArea: List<GPSPoint> = gson.fromJson(pointsJSON, type)
            return searchArea
        }
        set(value) {
            val pointsJSONString: String? = Gson().toJson(value)
            prefs.edit().putString(SEARCHAREA, pointsJSONString).apply()
        }


}