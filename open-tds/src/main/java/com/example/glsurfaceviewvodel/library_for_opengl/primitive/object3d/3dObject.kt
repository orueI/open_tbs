package com.example.glsurfaceviewvodel.library_for_opengl.primitive.object3d

import com.example.glsurfaceviewvodel.library_for_opengl.primitive.MutableInterface
import com.example.glsurfaceviewvodel.library_for_opengl.primitive.RenderPrimitive


abstract class ThreeDimensionalFigure {
    abstract fun getPrimitives(): List<RenderPrimitive>
}
abstract class ThreeDimensionalMutableFigure:ThreeDimensionalFigure(),MutableInterface
