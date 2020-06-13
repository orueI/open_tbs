package com.example.xube.test_opengl

import android.content.Context
import android.opengl.GLES20.*
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import android.util.Log
import com.example.glsurfaceviewvodel.R
import com.example.glsurfaceviewvodel.library_for_opengl.shader.JavaShaderUtils
import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Camera
import com.example.glsurfaceviewvodel.library_for_opengl.primitive.*
import com.example.glsurfaceviewvodel.library_for_opengl.shader.ShaderUtils
import com.example.glsurfaceviewvodel.library_for_opengl.viewmodel.ObservableCamera
import com.example.glsurfaceviewvodel.library_for_opengl.viewmodel.ObservablePrimitive
import com.example.glsurfaceviewvodel.library_for_opengl.viewmodel.ObserverCamera
import com.example.glsurfaceviewvodel.library_for_opengl.viewmodel.ObserverPrimitive
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.util.concurrent.CopyOnWriteArrayList
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10


class RenderOpenGl : GLSurfaceView.Renderer,
    ObserverPrimitive, ObserverCamera {

    private val context: Context

    private val POSITION_COUNT: Int = 3

    private var vertexData: FloatBuffer? = null
    private var uColorLocation: Int = 0
    private var aPositionLocation: Int = 0
    private var uMatrixLocation: Int = 0
    private var programId: Int = 0

    private val mProjectionMatrix = FloatArray(16)
    private val mViewMatrix = FloatArray(16)
    private val mMatrix = FloatArray(16)
    private val mModelMatrix = FloatArray(16)

    @Volatile private var listPrimitive: CopyOnWriteArrayList<RenderPrimitive> = CopyOnWriteArrayList()
    private var camera: Camera =
        Camera()

    constructor(
        context: Context,
        observablePrimitive: ObservablePrimitive,
        observableCamera: ObservableCamera
    ) {
        this.context = context
        prepareData()
        observablePrimitive.observe(this)
        observableCamera.observe(this)
    }

    override fun update(value: Camera) {
        camera = value
    }

    override fun update(value: List<RenderPrimitive>) {
//        listPrimitive = CopyOnWriteArrayList(value)
        listPrimitive.clear()
        listPrimitive.addAll(value)
//        prepareData()
//        bindData()
    }


    override fun onSurfaceCreated(arg0: GL10, arg1: EGLConfig) {
        glClearColor(255f, 255f, 255f, 1f)
        glEnable(GL_DEPTH_TEST)
        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)
        val vertexShaderId = JavaShaderUtils.createShader(
            context,
            GL_VERTEX_SHADER,
            R.raw.vertex_shader
        )
        val fragmentShaderId = JavaShaderUtils.createShader(
            context,
            GL_FRAGMENT_SHADER,
            R.raw.fragment_shader
        )
        programId = ShaderUtils.createProgram(vertexShaderId, fragmentShaderId)
        glUseProgram(programId)
        createViewMatrix()
        prepareData()
        bindData()
    }


    override fun onSurfaceChanged(arg0: GL10, width: Int, height: Int) {
        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)
        glViewport(0, 0, width, height)
        createProjectionMatrix(width, height)
        bindMatrix()
    }

    private fun prepareData() {

        val s = 0.4f
        val d = 0.9f
        val l = 3f

        var vertices = floatArrayOf()
        var firstLine = vertices.size / 3
//        synchronized(listPrimitive) {
            if (listPrimitive.isNotEmpty())
                for (i in listPrimitive){
                    vertices += i.prepareData(firstLine)
                    firstLine += i.quantityVertex
                }
//        }
        val bufer = StringBuffer()
        for (i:Float in vertices){
            bufer.append(i).append(" ")
        }
//        Log.v("vertices",bufer.toString())
        vertexData = ByteBuffer
            .allocateDirect(vertices.size * 4)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer()
        vertexData?.put(vertices)
    }


    private fun bindData() {
        // примитивы
        aPositionLocation = glGetAttribLocation(programId, "a_Position")
        vertexData?.position(0)
        glVertexAttribPointer(
            aPositionLocation, POSITION_COUNT, GL_FLOAT,
            false, 0, vertexData
        )
        glEnableVertexAttribArray(aPositionLocation)

        // цвет
        uColorLocation = glGetUniformLocation(programId, "u_Color")

        // матрица
        uMatrixLocation = glGetUniformLocation(programId, "u_Matrix")
    }

    private fun createProjectionMatrix(width: Int, height: Int) {
        var ratio = 1f
        var left = -1f
        var right = 1f
        var bottom = -1f
        var top = 1f
        val near = 2f
        val far = 12f
        if (width > height) {
            ratio = width.toFloat() / height
            left *= ratio
            right *= ratio
        } else {
            ratio = height.toFloat() / width
            bottom *= ratio
            top *= ratio
        }

        Matrix.frustumM(mProjectionMatrix, 0, left, right, bottom, top, near, far)
    }

    var x: Float = 1f
    var y: Float = 1f

    private fun createViewMatrix() {
        // точка положения камеры
//        val time:Float = SystemClock.uptimeMillis().toFloat()
//        val angle = (time * 2 * 3.1415926f)

        val eyeX = camera.eveVector3d.x
//        val eyeX = (Math.cos(angle.toDouble()) * 4f).toFloat()
        val eyeY = camera.eveVector3d.y
        val eyeZ = camera.eveVector3d.z
// точка направления камеры
        val centerX = camera.centerVector3d.x
        val centerY = camera.centerVector3d.y
        val centerZ = camera.centerVector3d.z
// up-вектор
        val upX = camera.upVector3d.x
        val upY = camera.upVector3d.y
        val upZ = camera.upVector3d.z

        Matrix.setLookAtM(
            mViewMatrix,
            0,
            eyeX.toFloat(),
            eyeY.toFloat(),
            eyeZ.toFloat(),
            centerX.toFloat(),
            centerY.toFloat(),
            centerZ.toFloat(),
            upX.toFloat(),
            upY.toFloat(),
            upZ.toFloat()
        )
    }


    private fun bindMatrix() {
        Matrix.multiplyMM(mMatrix, 0, mViewMatrix, 0, mModelMatrix, 0)
        Matrix.multiplyMM(mMatrix, 0, mProjectionMatrix, 0, mMatrix, 0)
        glUniformMatrix4fv(uMatrixLocation, 1, false, mMatrix, 0)
    }

    override fun onDrawFrame(arg0: GL10) {
        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)
        createViewMatrix()
        prepareData()
        bindData()

//        createViewMatrix()
        // оси
//        drawAxes()

        // треугольник
//        drawTriangle()

        drawPrimitives()
    }

//    private fun drawAxes() {
//        Matrix.setIdentityM(mModelMatrix, 0)
//        bindMatrix()
//
//        glLineWidth(3f)
//        glUniform4f(uColorLocation, 1.0f, 0.0f, 0.0f, 1.0f)
//        glDrawArrays(GL_LINES, 0, 2)
//
//        glUniform4f(uColorLocation, 0.0f, 0.0f, 1.0f, 1.0f)
//        glDrawArrays(GL_LINES, 2, 2)
//
//        glUniform4f(uColorLocation, 1.0f, 1.0f, 0.0f, 1.0f)
//        glDrawArrays(GL_LINES, 4, 2)
//    }

    private fun drawTriangle() {
        Matrix.setIdentityM(mModelMatrix, 0)
        setModelMatrix()
        bindMatrix()

        glUniform4f(uColorLocation, 0.0f, 1.0f, 0.0f, 1.0f)
        glDrawArrays(GL_TRIANGLE_STRIP, 6, 4)
    }

   private fun drawPrimitives() {
        Matrix.setIdentityM(mModelMatrix, 0)
        bindMatrix()
           for (i in listPrimitive){
               val c = i.color
               glUniform4f(uColorLocation, c.r, c.g, c.b, c.a)
               drawArrays(i)
           }
    }

    fun drawArrays(p: RenderPrimitive) {
        when (p) {
            is Triangles -> glDrawArrays(GL_TRIANGLES, p.firstLine, p.quantityVertex)
            is TriangleStrip -> glDrawArrays(GL_TRIANGLE_STRIP, p.firstLine, p.quantityVertex)
            is TriangleFan -> glDrawArrays(GL_TRIANGLE_FAN, p.firstLine, p.quantityVertex)

            is Lines -> {
                glLineWidth(p.width.toFloat())
                glDrawArrays(GL_LINES, p.firstLine, p.quantityVertex)
            }
            is LinesStrip -> {
                glLineWidth(p.width.toFloat())
                glDrawArrays(GL_LINE_STRIP, p.firstLine, p.quantityVertex)
            }
            is LinesLoop -> {
                glLineWidth(p.width.toFloat())
                glDrawArrays(GL_LINE_LOOP, p.firstLine, p.quantityVertex)
            }

            is Points -> glDrawArrays(GL_POINTS, p.firstLine, p.quantityVertex)
        }
    }

    private fun setModelMatrix() {
        Matrix.rotateM(mModelMatrix, 0, 125f, 0f, 0f, 1f)
    }

}