package com.example.glsurfaceviewvodel.library_for_opengl.primitive.object3d

import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Color
import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Vector3d
import com.example.glsurfaceviewvodel.library_for_opengl.primitive.*
import com.example.glsurfaceviewvodel.library_for_opengl.primitive.object2d.Rectangle
import org.apache.commons.math3.geometry.euclidean.threed.Line
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D



open class Parallelepiped : ThreeDimensionalMutableFigure{

    val topBrink: Rectangle
    val bottomBrink: Rectangle
    val leftBrink: Rectangle
    val rightBrink: Rectangle
    val frontBrink: Rectangle
    val behindBrink: Rectangle

    constructor(
        topBrink: Rectangle,
        bottomBrink: Rectangle,
        leftBrink: Rectangle,
        rightBrink: Rectangle,
        frontBrink: Rectangle,
        behindBrink: Rectangle
    ) {
        this.topBrink = topBrink
        this.bottomBrink = bottomBrink
        this.leftBrink = leftBrink
        this.rightBrink = rightBrink
        this.frontBrink = frontBrink
        this.behindBrink = behindBrink
    }

    constructor(p: Vector3d, width: Double, height: Double = width, length: Double = width) {
        this.topBrink = Rectangle.getRectXz(
                p.plusY(height).plusZ(-1 * length), width, height
        )
        this.bottomBrink = Rectangle.getRectXz(
                p.plusZ(-1 * length), width, length
        )
        this.leftBrink = Rectangle.getRectYz(
                p.plusZ(-1 * length), height, length
        )
        this.rightBrink =
            Rectangle.getRectYz(
                    p.plusX(width).plusZ(-1 * length), height, length
            )
        this.frontBrink = Rectangle.getRectXy(p, width, height)
        this.behindBrink = Rectangle.getRectXy(
                p.plusZ(-1 * length), width, height
        )
    }

    fun getCenter(): Vector3d =
        Vector3d(Line(frontBrink.point1, behindBrink.point4).intersection(Line(frontBrink.point2, behindBrink.point3)))

        override fun getPrimitives(): List<RenderPrimitive> =
        listOf(topBrink, bottomBrink, leftBrink, rightBrink, frontBrink, behindBrink)

    override fun move(vector3D: Vector3d) {
        topBrink.move(vector3D)
        bottomBrink.move(vector3D)
        leftBrink.move(vector3D)
        rightBrink.move(vector3D)
        frontBrink.move(vector3D)
        behindBrink.move(vector3D)
    }

    override fun turn(center: Vector3d, angle: Vector3d) {
        topBrink    .turn(center,angle)
        bottomBrink .turn(center,angle)
        leftBrink   .turn(center,angle)
        rightBrink  .turn(center,angle)
        frontBrink  .turn(center,angle)
        behindBrink .turn(center,angle)
    }
}

class Cube :
    Parallelepiped {

    constructor(
        topBrink: Rectangle,
        bottomBrink: Rectangle,
        leftBrink: Rectangle,
        rightBrink: Rectangle,
        frontBrink: Rectangle,
        behindBrink: Rectangle
    ) : super(topBrink, bottomBrink, leftBrink, rightBrink, frontBrink, behindBrink)

    constructor(p: Vector3d, width: Double) : super(p, width, width, width)

}
