package com.newpath.micopilot.network

import com.squareup.moshi.Json
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root

@Root(name = "response", strict = false)
data class TafDataList @JvmOverloads constructor(
    @field:Element(name = "request_index", required=false) var requestIndex: Int = 0,
//    @param:Element(name = "data_source", required=false) var dataSource: String,
//    @field:Element(name = "request", required=false) var request: String,
//    @field:Element(name = "errors", required=false) var errors: String,
//    @field:Element(name = "warnings", required=false) var warnings: String,
    @field:Element(name = "time_taken_ms", required=false) var timeTakenMs: Int = -1,
    @field:ElementList(inline=true, name = "TAF", required=false)
    @field:Path("data")
    var TAFS:List<TafDataModel>? = null
)

@Root(name = "TAF", strict = false)
data class TafDataModel @JvmOverloads constructor(

    @field:Element(name = "raw_text") var rawText: String = "",
    @field:Element(name = "station_id") var stationId: String = "",
    @field:Element(name = "issue_time") var issueTime: String = ""
//    @field:Element(name = "bulletin_time") var bulletinTime: String = "",
//    @field:Element(name = "valid_time_from") var validTimeFrom: String = "",
//    @field:Element(name = "valid_time_to") var validTimeTo: String = ""
//    var latitude: Double = 0.0,
//    var longitude: Double = 0.0,
//    @field:Element(name = "elevation_m") var elevationMeters: Double = 0.0

)