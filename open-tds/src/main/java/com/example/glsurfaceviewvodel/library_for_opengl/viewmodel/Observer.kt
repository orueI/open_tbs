package com.example.glsurfaceviewvodel.library_for_opengl.viewmodel

import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Camera
import com.example.glsurfaceviewvodel.library_for_opengl.primitive.RenderPrimitive

interface Observer<T> {
    fun update(value:T)
}
interface ObserverCamera {
    fun update(value: Camera)
}
interface ObserverPrimitive {
    fun update(value:List<RenderPrimitive>)
}
interface Observable<T>{
    fun observe(observer: Observer<T>)
}
interface ObservableCamera{
    fun observe(observer: ObserverCamera):Boolean
//    fun observe(function: (value:Camera) -> Unit) {
//        observe(object :ObserverCamera{
//            override fun update(value: Camera) {
//                function(value)
//            }
//        })
//    }
}
interface ObservablePrimitive{
    fun observe(observer:ObserverPrimitive ):Boolean
//    fun observe(function: (value:List<RenderPrimitive>) -> Unit) {
//        observe(object :ObserverPrimitive{
//            override fun update(value:List<RenderPrimitive>) {
//                function(value)
//            }
//        })
//    }
}