package com.example.glsurfaceviewvodel.library_for_opengl.primitive.object2d

import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Color
import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Vector3d
import com.example.glsurfaceviewvodel.library_for_opengl.primitive.*
import com.example.glsurfaceviewvodel.library_for_opengl.utils.moveVector
import com.example.glsurfaceviewvodel.library_for_opengl.utils.turnVectorOnAngleInCircle

open class Quadrangle : TriangleStrip,
    MutableInterface {

    var point1: Vector3d
    var point2: Vector3d
    var point3: Vector3d
    var point4: Vector3d

    constructor(
        point1: Vector3d,
        point2: Vector3d,
        point3: Vector3d,
        point4: Vector3d,
        color: Color = Color.BLACK
    ) : super() {
        this.point1 = point1
        this.point2 = point2
        this.point3 = point3
        this.point4 = point4
        this.color = color

    }

    override var color: Color
    override var firstLine: Int = 0
    override var quantityVertex: Int = 4

    override fun prepareData(firstLine: Int): FloatArray {
        this.firstLine = firstLine
        var vertex =
            point1.toFloatArray() + point2.toFloatArray() + point3.toFloatArray() + point4.toFloatArray()
        return vertex
    }

    fun getListVector3D(): List<Vector3d> = listOf(point1, point2, point3, point4)

    override fun move(vector3D: Vector3d) {
        point1 =
            moveVector(
                point1,
                vector3D
            )
        point2 =
            moveVector(
                point2,
                vector3D
            )
        point3 =
            moveVector(
                point3,
                vector3D
            )
        point4 =
            moveVector(
                point4,
                vector3D
            )
    }

    override fun turn(center: Vector3d, angle: Vector3d) {
        point1 =
            turnVectorOnAngleInCircle(
                center,
                point1,
                angle
            )
        point2 =
            turnVectorOnAngleInCircle(
                center,
                point2,
                angle
            )
        point3 =
            turnVectorOnAngleInCircle(
                center,
                point3,
                angle
            )
        point4 =
            turnVectorOnAngleInCircle(
                center,
                point4,
                angle
            )
    }

}

open class Rectangle :
    Quadrangle {
    constructor(
        point1: Vector3d,
        point2: Vector3d,
        point3: Vector3d,
        point4: Vector3d,
        color: Color = Color.BLACK
    ) : super(point1, point2, point3, point4, color)

    companion object {
        fun getRectXy(
            point1: Vector3d,
            winth: Double,
            height: Double,
            color: Color = Color.BLACK
        ): Rectangle =
            Rectangle(
                point1,
                point1.plusX(winth),
                point1.plusY(height),
                point1.plusX(winth).plusY(height), color
            )

        fun getRectYz(
            point1: Vector3d,
            height: Double,
            length: Double,
            color: Color = Color.BLACK
        ): Rectangle =
            Rectangle(
                point1,
                point1.plusZ(length),
                point1.plusY(height),
                point1.plusY(height).plusZ(length), color
            )

        fun getRectXz(
            point1: Vector3d,
            width: Double,
            length: Double,
            color: Color = Color.BLACK
        ): Rectangle =
            Rectangle(
                point1,
                point1.plusX(width),
                point1.plusZ(length),
                point1.plusX(width).plusZ(length), color
            )

    }
}

class Square : Rectangle {
    constructor(
        point1: Vector3d,
        point2: Vector3d,
        point3: Vector3d,
        point4: Vector3d,
        color: Color = Color.BLACK
    ) : super(point1, point2, point3, point4, color)

    private constructor(point1: Quadrangle) : super(
        point1.point1,
        point1.point2,
        point1.point3,
        point1.point4,
        point1.color
    )

    companion object {
        fun getSquareXy(point1: Vector3d, width: Double, color: Color = Color.BLACK): Square =
            Square(
                getRectXy(
                    point1,
                    width,
                    width,
                    color
                )
            )

        fun getSquareYz(point1: Vector3d, width: Double, color: Color = Color.BLACK): Square =
            Rectangle.getRectYz(
                point1,
                width,
                width,
                color
            ) as Square

        fun getSquareXz(point1: Vector3d, width: Double, color: Color = Color.BLACK): Square =
            Rectangle.getRectXz(
                point1,
                width,
                width,
                color
            ) as Square
    }

//    fun getCenter():Vector3D = ((point4 - point1) / Vector3D(2f,2f,2f)) + point1
}