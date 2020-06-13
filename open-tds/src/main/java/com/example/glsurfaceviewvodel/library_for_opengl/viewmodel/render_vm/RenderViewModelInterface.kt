package com.example.glsurfaceviewvodel.library_for_opengl.viewmodel.render_vm

import com.example.glsurfaceviewvodel.library_for_opengl.primitive.RenderPrimitive

interface RenderViewModelInterface {
    fun getPrimitives():List<RenderPrimitive>
}