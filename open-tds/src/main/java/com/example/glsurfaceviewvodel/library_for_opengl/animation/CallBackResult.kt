package com.example.glsurfaceviewvodel.library_for_opengl.animation

import com.example.glsurfaceviewvodel.library_for_opengl.primitive.MutableInterface

interface CallBackResult<T> {
    fun onNext(last:T, now:T)
//    fun onFinish(now:T)
}
interface AnimationInterface<T: MutableInterface> {
    fun onStart(figure:T)
    fun onNext( figure:T)
    fun onDestroy(figure:T)
}