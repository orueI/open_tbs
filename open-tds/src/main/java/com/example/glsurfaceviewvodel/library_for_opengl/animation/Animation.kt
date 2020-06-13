package com.example.glsurfaceviewvodel.library_for_opengl.animation

import com.example.glsurfaceviewvodel.library_for_opengl.primitive.MutableInterface
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D

abstract class Animation<T : MutableInterface> : Thread {
    var figure: T

   private val allTimeAnimation: Long
   private val quantityFramesUpdate: Int

   private val sleepTime: Long
  private  val callBack: AnimationInterface<T>
  protected  open var changeFigure: (figure: T) -> T = {it}
  protected  constructor(
        figure: T,
        allTimeAnimation: Long,
        quantityFramesUpdate: Int,
        callBack: AnimationInterface<T>) : super() {
        this.figure = figure
        this.callBack = callBack
        this.allTimeAnimation = allTimeAnimation
        this.quantityFramesUpdate = quantityFramesUpdate
        sleepTime = allTimeAnimation / quantityFramesUpdate
    }

    constructor(
        figure: T,
        allTimeAnimation: Long,
        quantityFramesUpdate: Int,
        callBack: AnimationInterface<T>,
        changeFigure: (figure: T) -> T
    ) : super() {
        this.figure = figure
        this.callBack = callBack
        this.allTimeAnimation = allTimeAnimation
        this.quantityFramesUpdate = quantityFramesUpdate
        this.changeFigure = changeFigure
        sleepTime = allTimeAnimation / quantityFramesUpdate
    }

    override fun run() {
        callBack.onStart(figure)
        for (i in 0 until quantityFramesUpdate) {
            figure = changeFigure(figure)
            callBack.onNext(figure)
            Thread.sleep(sleepTime)
        }
        callBack.onDestroy(figure)
        Thread.currentThread().interrupt()
    }
}
