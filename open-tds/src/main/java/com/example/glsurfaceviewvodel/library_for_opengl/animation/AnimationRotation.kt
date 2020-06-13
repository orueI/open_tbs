package com.example.glsurfaceviewvodel.library_for_opengl.animation

import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Vector3d
import com.example.glsurfaceviewvodel.library_for_opengl.primitive.MutableInterface

//class AnimationRotation<T : MutableInterface> : Animation<T> {
//    val center: Vector3D
//    val angle: Vector3D
//    val vector3DStep: Vector3D
//    var iteration = 0
//
//
//    constructor(
//        figure: T,
//        allTimeAnimation: Long,
//        quantityFramesUpdate: Int,
//        center: Vector3D,
//        angle: Vector3D,
//        callBack: AnimationInterface<T>
//    ) : super(figure, allTimeAnimation, quantityFramesUpdate, callBack) {
//        this.figure = figure
//
//        this.center = center
//        this.angle = angle
//        vector3DStep = Vector3D(
//            angle.x / quantityFramesUpdate,
//            angle.y / quantityFramesUpdate,
//            angle.z / quantityFramesUpdate
//        )
//        sleepTime = allTimeAnimation / quantityFramesUpdate
//    }
//
//    override fun run() {
//        var last = figure
//        for (i in 1..quantityFramesUpdate) {
//            if (iteration == quantityFramesUpdate) {
//                callBack(last, figure)
//                Thread.currentThread().interrupt()
//            } else {
//                figure.turn(
//                    center,
//                    vector3DStep
//                )
//                callBack(last, figure)
//                iteration++
//                sleep(sleepTime)
//            }
//        }
//    }

class AnimationRotation<T : MutableInterface> : Animation<T> {
    private val center: Vector3d
//    private val angle: Vector3D
    private val vector3DStep: Vector3d


    constructor(
        figure: T,
        allTimeAnimation: Long,
        quantityFramesUpdate: Int,
        center: Vector3d,
        angle: Vector3d,
        callBack: AnimationInterface<T>
    ) : super(figure, allTimeAnimation, quantityFramesUpdate, callBack) {
        this.center = center
//        this.angle = angle
        vector3DStep = Vector3d(
            angle.x / quantityFramesUpdate,
            angle.y / quantityFramesUpdate,
            angle.z / quantityFramesUpdate
        )
        changeFigure = {
            it.turn(this.center, vector3DStep)
            it
        }
    }
}
