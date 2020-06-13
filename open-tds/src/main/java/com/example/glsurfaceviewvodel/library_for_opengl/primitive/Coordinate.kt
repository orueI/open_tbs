package com.example.glsurfaceviewvodel.library_for_opengl.primitive

import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Vector3d


//fun Vector3d.toFloatArray(): FloatArray = floatArrayOf(x.toFloat(), y.toFloat(), z.toFloat())
//
//fun Vector3d.plusX(x: Double): Vector3d = Vector3d(add(Vector3d(x, 0.0, 0.0)))
//
//fun Vector3d.plusY(y: Double): Vector3d = Vector3d(add(Vector3d(0.0, y, 0.0)))
//fun Vector3d.plusZ(z: Double): Vector3d = Vector3d(add(Vector3d(0.0, 0.0, z)))
//
//operator fun Vector3d.plus(p: Vector3d): Vector3d = Vector3d(add(p))
//operator fun Vector3d.minus(p: Vector3d): Vector3d = Vector3d(add(Vector3d(-1 * p.x, -1 * p.y, -1 * p.z)))
//operator fun Vector3d.times(p: Vector3d): Vector3d = Vector3d(x * p.x, y * p.y,z * p.z)
//operator fun Vector3d.div(p: Vector3d): Vector3d = Vector3d(x / p.x, y / p.y,z / p.z)






























//import com.example.glsurfaceviewvodel.library_for_opengl.angleAccounting

//open class Vector2D {
//    open var x: Float = 0f
//    open var y: Float = 0f
//
//    open var r: Float = 0f // radius
//    open var a: Float = 0f // angle alpha
//
//
//    constructor()
//    constructor(x: Float, y: Float) {
//        this.x = x
//        this.y = y
//    }
//
//    constructor(vector2D: Vector2D) {
//        this.x = vector2D.x
//        this.y = vector2D.y
//
//        this.r = vector2D.r
//        this.a = vector2D.a
//    }
//
//
//    open fun copy(): Vector2D = Vector2D(this)
//
//    open fun calculateRectCoordinateSystem() {
//        x = (r * cos(Math.toRadians(a.toDouble())).toFloat())
//        y = (r * sin(Math.toRadians(a.toDouble()))).toFloat()
//    }
//    open fun calculateAngleCoordinateSystem() {
//        r = sqrt(x * x + y * y)
//        a = Math.toDegrees(atan(y / x).toDouble()).toFloat()
//
//    }
//
//    open fun toFloatArray(): FloatArray = floatArrayOf(x, y)
//
//    operator fun plus(p:    Vector2D):     Vector2D = Vector2D(x + p.x, y + p.y)
//    operator fun minus(p:   Vector2D):    Vector2D = Vector2D(x - p.x, y - p.y)
//    operator fun times(p:   Vector2D):    Vector2D = Vector2D(x * p.x, y * p.y)
//    operator fun div(p:     Vector2D):      Vector2D = Vector2D(x / p.x, y / p.y)
//}


//class Vector2DTransformative : Vector2D {
//    override var x: Float = 0f
//        set(value) {
//            field = value
//            calculateAngleCoordinateSystem()
//        }
//    override var y: Float = 0f
//        set(value) {
//            field = value
//            calculateAngleCoordinateSystem()
//        }
//
//    override var r: Float = 0f // radius
//        set(value) {
//            field = value
//            calculateRectCoordinateSystem()
//        }
//    override var a: Float = 0f // angle alpha
//        set(value) {
//            field = value
//            calculateRectCoordinateSystem()
//        }
//
//    constructor() : super()
//    constructor(x: Float, y: Float) : super(x, y)
//    constructor(vector2D: Vector2D) {
//        this.x = vector2D.x
//        this.y = vector2D.y
//
//        this.r = vector2D.r
//        this.a = vector2D.a
//    }
//
//
//}

//open class Vector3D  {   }

//    constructor(v: Vector3D) : super(v.x, v.y, v.z)
//    constructor(x: Double, y: Double, z: Double) : super(x, y, z)
//    constructor(v: DoubleArray?) : super(v)
//    constructor(alpha: Double, delta: Double) : super(alpha, delta)
//    constructor(a: Double, u: Vector3D?) : super(a, u)
//    constructor(a1: Double, u1: Vector3D?, a2: Double, u2: Vector3D?) : super(a1, u1, a2, u2)
//    constructor(
//        a1: Double,
//        u1: Vector3D?,
//        a2: Double,
//        u2: Vector3D?,
//        a3: Double,
//        u3: Vector3D?
//    ) : super(a1, u1, a2, u2, a3, u3)
//
//    constructor(
//        a1: Double,
//        u1: Vector3D?,
//        a2: Double,
//        u2: Vector3D?,
//        a3: Double,
//        u3: Vector3D?,
//        a4: Double,
//        u4: Vector3D?
//    ) : super(a1, u1, a2, u2, a3, u3, a4, u4)
//
//
//    fun copy(): Vector3d = Vector3d(x, y, z)
//
//

//fun Vector3d.plusX(value: Float): Vector3d {
//    x += value
//    return this
//}
//
//fun Vector3d.plusY(value: Float): Vector3d {
//    y += value
//    return this
//}
//
//fun Vector3d.plusZ(value: Float): Vector3d {
//    z += value
//    return this
//}
//
//fun Vector3d.plusXy(x: Float, y: Float): Vector3d {
//    plusX(x)
//    plusY(y)
//    return this
//}
//
//fun Vector3d.plusXyz(x: Float, y: Float, z: Float): Vector3d {
//    plusX(x)
//    plusY(y)
//    plusZ(z)
//    return this
//}


//                                 ________         blue=Y^             ^Z yellow
//                                /\      /\               \          /
//                               /  \    /  /               \        /
//                      left->  /___/___/  /                 \      /
//x = width                     \  /   \  /                   \    /
//y = height                     \/_____\/                     \  /
//z = length                     ^   ^                          \/-------------->X = red
//                               |   |front
//                            point0