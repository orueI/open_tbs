package com.example.glsurfaceviewvodel.library_for_opengl.dataclass

import com.example.glsurfaceviewvodel.library_for_opengl.primitive.MutableInterface
import com.example.glsurfaceviewvodel.library_for_opengl.utils.moveVector
import com.example.glsurfaceviewvodel.library_for_opengl.utils.turnVectorOnAngleInCircle

data class Camera(

// точка положения камеры
    var eveVector3d: Vector3d = Vector3d(2.0,2.0,3.0),
//    var eyeX: Float = 2f,
//    var eyeY: Float = 2f,
//    var eyeZ: Float = 03f,

// точка направления камеры
    var centerVector3d: Vector3d = Vector3d(0.0, 0.0, 0.0),
//    var centerX: Float = 0f,
//    var centerY: Float = 0f,
//    var centerZ: Float = 0f,

// up-вектор
    var upVector3d: Vector3d = Vector3d(0.0,1.0,0.0)
//    var upX: Float = 0f,
//    var upY: Float = 1f,
//    var upZ: Float = 0f
): MutableInterface {
    override fun move(vector3D: Vector3d) {
        mutableEveVector.move(vector3D)
    }

    override fun turn(center: Vector3d, angle: Vector3d) {
        mutableEveVector.turn(center,angle)
    }

    val mutableEveVector:MutableInterface = object :MutableInterface{
        override fun move(vector3d: Vector3d) {
            eveVector3d = moveVector(eveVector3d, vector3d)
        }

        override fun turn(center: Vector3d, angle: Vector3d) {
            eveVector3d = turnVectorOnAngleInCircle(center, eveVector3d, angle)
        }
    }

    val mutableCenterVector:MutableInterface = object :MutableInterface{
        override fun move(vector3D: Vector3d) {
            centerVector3d = moveVector(centerVector3d, vector3D)
        }

        override fun turn(center: Vector3d, angle: Vector3d) {
            centerVector3d = turnVectorOnAngleInCircle(center, centerVector3d, angle)
        }
    }

    val mutableUpVector:MutableInterface = object :MutableInterface{
        override fun move(vector3D: Vector3d) {
            upVector3d = moveVector(upVector3d, vector3D)
        }

        override fun turn(center: Vector3d, angle: Vector3d) {
            upVector3d = turnVectorOnAngleInCircle(center, upVector3d, angle)
        }
    }

}