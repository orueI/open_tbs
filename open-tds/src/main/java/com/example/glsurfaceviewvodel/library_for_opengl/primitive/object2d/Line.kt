package com.example.glsurfaceviewvodel.library_for_opengl.primitive.object2d

import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Color
import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Vector3d
import com.example.glsurfaceviewvodel.library_for_opengl.primitive.Lines
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D


class Line: Lines {
    override var color: Color
    override var firstLine: Int = 0
    override var quantityVertex: Int = 2

    var pointStart: Vector3d
    var pointFinish: Vector3d

    constructor(pointStart: Vector3d, pointFinish: Vector3d, color: Color,width:Double) : super() {
        this.pointStart = pointStart
        this.pointFinish = pointFinish
        this.color = color
        this.width = width
    }
    constructor(pointStart: Vector3d, pointFinish: Vector3d, color: Color) : super() {
        this.pointStart = pointStart
        this.pointFinish = pointFinish
        this.color = color
    }

    override fun prepareData(firstLine: Int): FloatArray {
        this.firstLine = firstLine
        return pointStart.toFloatArray() + pointFinish.toFloatArray()
    }

}