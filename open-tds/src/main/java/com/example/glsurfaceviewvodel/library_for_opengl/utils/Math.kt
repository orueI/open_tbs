package com.example.glsurfaceviewvodel.library_for_opengl.utils

import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Vector3d
import org.apache.commons.math3.geometry.euclidean.threed.Rotation
import org.apache.commons.math3.geometry.euclidean.threed.RotationOrder

fun turnVectorOnAngleInCircle(center: Vector3d, vector3D: Vector3d, angle: Vector3d): Vector3d {
    var tmp = ((vector3D) - (center))
    val rotation = Rotation(RotationOrder.XYZ,Math.toRadians(angle.x),Math.toRadians(angle.y),Math.toRadians(angle.z))
    tmp = Vector3d(rotation.applyInverseTo(tmp))
    return (tmp + center)
}

fun moveVector(vector3D: Vector3d, vectorMove: Vector3d): Vector3d = vector3D + vectorMove

fun vectorToModule(vector3D: Vector3d): Vector3d {
    var x = if (vector3D.x < 0) -1.0 else 1.0
    var y = if (vector3D.y < 0) -1.0 else 1.0
    var z = if (vector3D.z < 0) -1.0 else 1.0
    return Vector3d(x, y, z)
}

fun module(vector3D: Vector3d): Vector3d {
    var x = if (vector3D.x < 0) -1.0 * vector3D.x else vector3D.x
    var y = if (vector3D.y < 0) -1.0 * vector3D.y else vector3D.y
    var z = if (vector3D.z < 0) -1.0 * vector3D.z else vector3D.z
     return Vector3d(x, y, z)
}

//fun isOneFourth(center: Vector2D, p1: Vector2D, p2: Vector2D): Boolean =
//    !((center.x > p1.x) xor (center.x > p2.x)) && !((center.y > p1.y) xor (center.y > p2.y))
//
//fun isOneFourth(center: Vector2D, p1: Vector2D, angle: Float): Boolean {
//    val angleCenterPoint = getAngleBetweenP1AndAxisX(center, p1)
//    return ((angle % 90) + angleCenterPoint < 90)
//}
//
//fun getAngleBetweenP1AndAxisX(p0: Vector2D, p1: Vector2D): Float =
//    atan(abs(p0.y - p1.y) / abs(p0.x - p1.x))

//fun angleAccounting(angle: Float): Int {
//    val angle = angle % 360
//    if (angle > 0f && angle < 90) return 1
//    else if (angle > 90f && angle < 180) return 2
//    else if (angle > 180f && angle < 270) return 3
//    else if (angle > 270f && angle < 360) return 4
//    else return 0
//}

//fun getConvexContour(list: List<Vector3D>): Vector3D {
//    var coordinate = 0
//    for (i in list.indices) {
//        if (list[i - 1].x == list[i].x) coordinate = 0
//        if (list[i - 1].y == list[i].y) coordinate = 1
//        if (list[i - 1].z == list[i].z) coordinate = 2
//    }
//
//}

//fun getConvexContour(list: List<Vector3D>, coordinate: Int) { // алгоритм джарвис
//    var headV = list[0]
//    val firstV = headV
//    var lastV = headV
//    val listMBO = ArrayList<Vector3D>()
//    val f = true
//    do {
//        if (listMBO.size == 1) {
//
//        }
//        var i = 1
//        var minCos = 11f
//        for (j in list.indices) {
//            if (list[j] == lastV || list[j] == headV)
//                continue
//            val cos = getCos(headV, lastV, list[j])
//            if (cos < minCos) {
//                minCos = cos
//                i = j
//            }
//        }
//        lastV = headV
//        headV = list[i]
//        f = !check
//    }while (f)
//}


//fun getCos(headVector: Vector3D, v1: Vector3D, v2: Vector3D): Float {
//    val vectorHead = headVector - v1
//    val vector = headVector - v2
//    vector.calculateAngleCoordinateSystem()
//    vectorHead.calculateAngleCoordinateSystem()
//
//    val multiply = vectorHead * vector
//    return (multiply.x + multiply.y + multiply.z) / (vector.r + vectorHead.r)
//}
//
//}
