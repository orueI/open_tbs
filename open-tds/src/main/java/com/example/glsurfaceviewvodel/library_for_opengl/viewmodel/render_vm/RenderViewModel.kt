package com.example.glsurfaceviewvodel.library_for_opengl.viewmodel.render_vm

import androidx.lifecycle.ViewModel
import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Camera
import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Color
import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Vector3d
import com.example.glsurfaceviewvodel.library_for_opengl.primitive.RenderPrimitive
import com.example.glsurfaceviewvodel.library_for_opengl.primitive.object3d.ThreeDimensionalFigure
import com.example.glsurfaceviewvodel.library_for_opengl.primitive.object2d.Line
import com.example.glsurfaceviewvodel.library_for_opengl.viewmodel.ObservableCamera
import com.example.glsurfaceviewvodel.library_for_opengl.viewmodel.ObservablePrimitive
import com.example.glsurfaceviewvodel.library_for_opengl.viewmodel.ObserverCamera
import com.example.glsurfaceviewvodel.library_for_opengl.viewmodel.ObserverPrimitive

open class RenderViewModel : ViewModel,
    ObservablePrimitive,
    ObservableCamera {
    private val listPrimitive = ArrayList<RenderPrimitive>()
    private var camera: Camera = Camera()
    private val listObserversPrimitive = ArrayList<ObserverPrimitive>()
    private val listObserversCamera = ArrayList<ObserverCamera>()
    var isShowAxes = true
        set(value) {
            if (!value)
                listPrimitive.removeAll(createAxes())
            field = value
        }

    constructor() {
        if (isShowAxes)
            addPrimitive(createAxes())
    }

    fun setCamera(camera: Camera) {
        this.camera = camera
        notifyForUpdateCamera()
    }

    fun addPrimitive(p: RenderPrimitive) {
        listPrimitive.add(p)
        notifyForUpdatePrimitives()
    }

    fun addPrimitive(p: List<RenderPrimitive>) {
        listPrimitive.addAll(p)
        notifyForUpdatePrimitives()
    }

    fun addPrimitive(p: ThreeDimensionalFigure) {
        listPrimitive.addAll(p.getPrimitives())
        notifyForUpdatePrimitives()
    }

    fun addPrimitive(p: Collection<ThreeDimensionalFigure>) {
        p.forEach {
            listPrimitive.addAll(it.getPrimitives())
        }
        notifyForUpdatePrimitives()
    }

    fun removePrimitive(p: RenderPrimitive){
        listPrimitive.remove(p)
        notifyForUpdatePrimitives()
    }

    fun removePrimitive(p: List<RenderPrimitive>){
        listPrimitive.removeAll(p)
        notifyForUpdatePrimitives()
    }

    fun removePrimitive(p: Collection<ThreeDimensionalFigure>){
        p.forEach {
            listPrimitive.removeAll(it.getPrimitives())
        }
        notifyForUpdatePrimitives()
    }

    fun removePrimitive(p: ThreeDimensionalFigure){
        listPrimitive.removeAll(p.getPrimitives())
        notifyForUpdatePrimitives()
    }


    fun clear() {
        listPrimitive.clear()
        if (isShowAxes)
            addPrimitive(createAxes())
        notifyForUpdatePrimitives()
    }

    private fun createAxes(): ArrayList<Line> {
        val listAxes = ArrayList<Line>()
        listAxes.add(
            Line(
                Vector3d(-3.0, 0.0, 0.0),
                Vector3d(3.0, 0.0, 0.0),
                Color(1.0f, 0.0f, 0.0f, 1.0f)
            )
        )
        listAxes.add(
            Line(
                Vector3d(0.0, -3.0, 0.0),
                Vector3d(0.0, 3.0, 0.0),
                Color(0.0f, 0.0f, 1.0f, 1.0f)
            )
        )
        listAxes.add(
            Line(
                Vector3d(0.0, 0.0, -3.0),
                Vector3d(0.0, 0.0, 3.0),
                Color(0.01f,0.65f,0.12f,1f)
            )
        )

        return listAxes
    }

    private fun notifyForUpdatePrimitives() {
        listObserversPrimitive.forEach { it.update(listPrimitive) }
    }

    private fun notifyForUpdateCamera() {
        listObserversCamera.forEach { it.update(camera) }
    }

    override fun observe(observer: ObserverCamera): Boolean {
        observer.update(camera)
        return listObserversCamera.add(observer)
    }

    override fun observe(observer: ObserverPrimitive): Boolean {
        observer.update(listPrimitive)
        return listObserversPrimitive.add(observer)
    }

}