package com.example.glsurfaceviewvodel.library_for_opengl.dataclass

public class Color(
    val r: Float,
    val g: Float,
    val b: Float,
    val a: Float = 1f
) {
   companion object {
        val RED         = Color(1f, 0f,  0f,1f)
        val GREEN       = Color(0f, 1f, 0f,1f)
        val DarkGreen   = Color(0.01f,0.37f,0.12f,1f)
        val BLUE        = Color(0f, 1f, 0f,1f)
        val YELLOW      = Color(1f, 1f, 0f,1f)
        val ORANGE      = Color(0.94f,0.59f,0f,1f)
        val WHITE       = Color(1f, 1f, 1f,1f)
        val BLACK       = Color(0f, 0f, 0f,1f)
    }

}