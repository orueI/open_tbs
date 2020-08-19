package com.example.glsurfaceviewvodel.library_for_opengl.dataclass

import org.apache.commons.math3.exception.DimensionMismatchException
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D

class Vector3d: Vector3D {
    constructor(vector:Vector3D):super(vector.x,vector.y,vector.z)

    constructor(x: Double, y: Double, z: Double) :super(x,y, z)

    @Throws(DimensionMismatchException::class)
    constructor(v: DoubleArray) :super(v)

    constructor(alpha: Double, delta: Double) :super(alpha, delta)

    constructor(a: Double, u: Vector3D) :super(a,u)

    constructor(a1: Double, u1: Vector3D, a2: Double, u2: Vector3D) :super(a1,u1, a2, u2)

    constructor(
        a1: Double,
        u1: Vector3D,
        a2: Double,
        u2: Vector3D,
        a3: Double,
        u3: Vector3D
    ):super(a1, u1, a2, u2, a3, u3)

    constructor(
        a1: Double,
        u1: Vector3D,
        a2: Double,
        u2: Vector3D,
        a3: Double,
        u3: Vector3D,
        a4: Double,
        u4: Vector3D
    ) : super(a1, u1, a2, u2, a3, u3, a4, u4)

    fun toFloatArray(): FloatArray = floatArrayOf(x.toFloat(), y.toFloat(), z.toFloat())

    fun plusX(x: Double): Vector3d = Vector3d(add(Vector3d(x, 0.0, 0.0)))

    fun plusY(y: Double): Vector3d = Vector3d(add(Vector3d(0.0, y, 0.0)))
    fun plusZ(z: Double): Vector3d = Vector3d(add(Vector3d(0.0, 0.0, z)))

    operator fun plus(p: Vector3d): Vector3d = Vector3d(add(p))
    operator fun minus(p: Vector3d): Vector3d = Vector3d(add(Vector3d(-1 * p.x, -1 * p.y, -1 * p.z)))
    operator fun times(p: Vector3d): Vector3d = Vector3d(x * p.x, y * p.y,z * p.z)
    operator fun div(p: Vector3d): Vector3d = Vector3d(x / p.x, y / p.y,z / p.z)
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false
        return true
    }


}