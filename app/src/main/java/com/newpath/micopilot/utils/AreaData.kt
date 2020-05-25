package com.newpath.micopilot.utils

import com.newpath.micopilot.models.GPSPoint

/**
 * generates 4 gps points that makes up a box area to be used to search for airport data
 */
class AreaData(origin: GPSPoint) {

    /* the origin that the area will be created at*/
    private var origin: GPSPoint = origin
    /* (half) the width of the line at the beginning of the box, in minutes */
    private var startWidth: Float = 0.03f
    /* (half) the width of the line at the end of the box, in minutes*/
    private var endWidth: Float = 0.06f
    /* the length of our area box, in minutes */
    private var length: Float = 1.0f

    /**
     * in case we want to override the default gps area properties
     * @param origin - users current location
     * @param startWidth - (half) the width of the line at the beginning of the box, in minutes
     * @param endWidth - (half) the width of the line at the end of the box, in minutes
     * @param length - the length of our area box, in minutes
     */
    constructor(origin: GPSPoint, startWidth: Float, endWidth: Float, length: Float) : this(origin) {
        this.startWidth = startWidth
        this.endWidth = endWidth
        this.length = length
    }

    /**
     * generates our four points to define our search area
     * @param - rotation to apply to search area given in radians
     */
    fun generateMapArea(rotationAngle: Double): List<GPSPoint>{

        var startPointLeft: GPSPoint = GPSPoint(origin.X-startWidth, origin.Y)
        var startPointRight: GPSPoint = GPSPoint(origin.X+startWidth, origin.Y)
        var endPointLeft: GPSPoint = GPSPoint(origin.X-endWidth, origin.Y + length)
        var endPointRight: GPSPoint = GPSPoint(origin.X+endWidth, origin.Y + length)

        startPointLeft.rotate(origin, rotationAngle)
        startPointRight.rotate(origin, rotationAngle)
        endPointLeft.rotate(origin, rotationAngle)
        endPointRight.rotate(origin, rotationAngle)


        return listOf(startPointLeft,startPointRight,endPointLeft,endPointRight)

    }

}