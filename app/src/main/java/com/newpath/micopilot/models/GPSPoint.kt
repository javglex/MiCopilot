package com.newpath.micopilot.models

import kotlin.math.cos
import kotlin.math.sin

class GPSPoint() {
    var X : Double = 0.0
    var Y : Double = 0.0

    constructor(x: Double, y: Double):this(){
        this.X = x
        this.Y = y
    }

    /**
     * Difference between this point and the origin
     * returns a new point with X and Y differences
     */
    operator fun minus(origin: GPSPoint) : GPSPoint{
       var diff: GPSPoint = GPSPoint()

        diff.X = X - origin.X
        diff.Y = Y - origin.Y

        return diff
    }

    /**
     * Difference between this point and the origin
     * returns a new point with X and Y differences
     */
    operator fun plus(origin: GPSPoint) : GPSPoint{
        var sum: GPSPoint = GPSPoint()

        sum.X = X + origin.X
        sum.Y = Y + origin.Y

        return sum
    }

    /**
     * Rotates this point around an origin
     * @param origin - point to rotate about
     * @param angle - amount to rotate by in radians
     */
    fun rotate(origin: GPSPoint, angle: Double): GPSPoint{

        var translated: GPSPoint = this-origin
        var rotated: GPSPoint = GPSPoint()

        rotated.X = translated.X * cos(angle) - translated.Y * sin(angle)
        rotated.Y = translated.X * sin(angle) + translated.Y * cos(angle)

        this.X = rotated.X + origin.X
        this.Y = rotated.Y + origin.Y

        return this
    }

    override fun toString(): String = "( "+this.X + ", " + this.Y + " )"

}