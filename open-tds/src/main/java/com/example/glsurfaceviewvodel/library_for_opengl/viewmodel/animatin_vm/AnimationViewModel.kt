package com.example.glsurfaceviewvodel.library_for_opengl.viewmodel.animatin_vm

import com.example.glsurfaceviewvodel.library_for_opengl.animation.Animation
import com.example.glsurfaceviewvodel.library_for_opengl.animation.AnimationInterface
import com.example.glsurfaceviewvodel.library_for_opengl.animation.AnimationMove
import com.example.glsurfaceviewvodel.library_for_opengl.animation.AnimationRotation
import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Vector3d
import com.example.glsurfaceviewvodel.library_for_opengl.primitive.object3d.ThreeDimensionalMutableFigure
import com.example.glsurfaceviewvodel.library_for_opengl.viewmodel.render_vm.RenderViewModel
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D

open class AnimationViewModel : RenderViewModel() {

   protected val list3dObject = ArrayList<ThreeDimensionalMutableFigure>()
   protected val animationInterface: AnimationInterface<ThreeDimensionalMutableFigure> = object :AnimationInterface<ThreeDimensionalMutableFigure>{
        override fun onStart(figure: ThreeDimensionalMutableFigure) {
            removeMutable3dObject(figure)
            addPrimitive(figure.getPrimitives())
        }

        override fun onNext(figure: ThreeDimensionalMutableFigure) {}

        override fun onDestroy(figure: ThreeDimensionalMutableFigure) {
            removePrimitive(figure)
            addMutable3dObject(figure)
        }

    }

    fun get(i:Int):ThreeDimensionalMutableFigure = list3dObject[i]

    fun get(figure:ThreeDimensionalMutableFigure):ThreeDimensionalMutableFigure? = list3dObject.find { it == figure }

    fun animationMove3dObject(
        object3d: ThreeDimensionalMutableFigure,
        timeAnimation: Long,
        quantityFramesUpdate: Int,
        vector3D: Vector3d
    ) {// todo cast exception, if object3d not found
        if (findMutable3dObject(object3d)) {
            AnimationMove(
                object3d,
                timeAnimation,
                quantityFramesUpdate,
                vector3D,
                animationInterface
            ).start()
        }
    }

    fun animationRotate3dObject(
        object3d: ThreeDimensionalMutableFigure,
        timeAnimation: Long,
        quantityFramesUpdate: Int,
        vector3DCenter: Vector3d,
        vector3D: Vector3d
    ) {// todo cast exception, if object3d not found
        if (findMutable3dObject(object3d)) {
            AnimationRotation(
                object3d,
                timeAnimation,
                quantityFramesUpdate,
                vector3DCenter,
                vector3D,
                animationInterface
            ).start()
        }
    }

    fun animation3dObject(animation: Animation<ThreeDimensionalMutableFigure>){
        if (findMutable3dObject(animation.figure)){
            animation.start()
        }
    }

    fun addMutable3dObject(object3d: ThreeDimensionalMutableFigure) {
        list3dObject.add(object3d)
        addPrimitive(object3d)
    }

    fun removeMutable3dObject(object3d: ThreeDimensionalMutableFigure) {
        list3dObject.remove(object3d)
        removePrimitive(object3d)
    }

    fun findMutable3dObject(object3d: ThreeDimensionalMutableFigure): Boolean =
        list3dObject.contains(object3d)
}