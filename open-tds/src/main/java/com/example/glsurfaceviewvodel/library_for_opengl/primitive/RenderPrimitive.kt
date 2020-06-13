package com.example.glsurfaceviewvodel.library_for_opengl.primitive

import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Color
import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Vector3d
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D

  abstract class RenderPrimitive {
    abstract var firstLine:Int
    abstract var color: Color
    abstract var quantityVertex:Int
    protected set
    abstract fun prepareData(firstLine:Int):FloatArray
}

abstract class LinePrimitive: RenderPrimitive() {
    var width:Double = 1.0
}

interface MutableInterface{
    abstract fun move(vector3D: Vector3d)
    abstract fun turn(center: Vector3d, angle: Vector3d)
}

abstract class Triangles    : RenderPrimitive()
abstract class TriangleStrip: RenderPrimitive()
abstract class TriangleFan  : RenderPrimitive()

abstract class Lines        : LinePrimitive()
abstract class LinesStrip   : LinePrimitive()
abstract class LinesLoop    : LinePrimitive()

abstract class Points       : RenderPrimitive()


abstract class TrianglesMutable    : Triangles    (), MutableInterface
abstract class TriangleStripMutable: TriangleStrip(), MutableInterface
abstract class TriangleFanMutable  : TriangleFan  (), MutableInterface

abstract class LinesMutable        : Lines        (), MutableInterface
abstract class LinesStripMutable   : LinesStrip   (), MutableInterface
abstract class LinesLoopMutable    : LinesLoop    (), MutableInterface

abstract class PointsMutable       : Points       (), MutableInterface