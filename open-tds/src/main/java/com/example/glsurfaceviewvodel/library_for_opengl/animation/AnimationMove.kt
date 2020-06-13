package com.example.glsurfaceviewvodel.library_for_opengl.animation

import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Vector3d
import com.example.glsurfaceviewvodel.library_for_opengl.primitive.MutableInterface
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D

class AnimationMove<T : MutableInterface> : Animation<T> {
    private val vector3DStep: Vector3D

    constructor(
        figure: T,
        allTimeAnimation: Long,
        quantityFramesUpdate: Int,
        vectorMove: Vector3D,
        callBack: AnimationInterface<T>
    ) : super(figure, allTimeAnimation, quantityFramesUpdate, callBack) {
        vector3DStep = Vector3d(
            vectorMove.x / quantityFramesUpdate,
            vectorMove.y / quantityFramesUpdate,
            vectorMove.z / quantityFramesUpdate
        )
        changeFigure = {
            it.move(vector3DStep)
            it
        }
    }
}