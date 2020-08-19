package com.example.glsurfaceviewvodel.library_for_opengl.animation

import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Vector3d
import com.example.glsurfaceviewvodel.library_for_opengl.primitive.MutableInterface

class AnimationRotation<T : MutableInterface> : Animation<T> {
    private val center: Vector3d
    private val vector3DStep: Vector3d


    constructor(
        figure: T,
        allTimeAnimation: Long,
        quantityFramesUpdate: Int,
        center: Vector3d,
        angle: Vector3d,
        callBack: AnimationInterface<T>?
    ) : super(figure, allTimeAnimation, quantityFramesUpdate, callBack) {
        this.center = center
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
