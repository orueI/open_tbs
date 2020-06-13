package com.example.xube

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Vector3d
import com.example.glsurfaceviewvodel.library_for_opengl.primitive.object3d.Cube
import com.example.glsurfaceviewvodel.library_for_opengl.viewmodel.animatin_vm.AnimationViewModel
import com.example.xube.test_opengl.RenderOpenGl
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnTouchListener {
    lateinit var vm: AnimationViewModel
  lateinit  var cube: Cube


    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//        if (event?.action == MotionEvent.ACTION_MOVE) {
//            xy.chang(event.x, event.y)
        layuot.setDrawingCacheEnabled(true)
        layuot.buildDrawingCache()
        val c = layuot.getDrawingCache(true).getPixel(layuot.width / 3, layuot.height / 2)
        Log.v("MyTag", c.toString())
//        }
        return true
    }


//    private lateinit var glSurfaceView: GLSurfaceView

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)getResources
//        setContentView(R.layout.activity_main)
//    }

//    override fun onStart() {
//        super.onStart()
//
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        var mMatrix = floatArrayOf(3f, 0f, 0f)
//        l(mMatrix.toString())
//        Matrix.rotateM(mMatrix, 0, 0f, 0f, 0f, 1f)
//        l(mMatrix.toString())

//    }
//    {
        if (!supportES2()) {
            Toast.makeText(this, "OpenGl ES 2.0 is not supported", Toast.LENGTH_LONG).show()
            finish()
            return
        }
//        glSurfaceView = GLSurfaceView(this)
        glSurfaceView.setEGLContextClientVersion(2)
        vm = ViewModelProviders.of(this).get(AnimationViewModel::class.java)
        val render =
            RenderOpenGl(
                this,
                vm,
                vm
            )
        cube =
        Cube(
            Vector3d(-1.0, -0.5, -1.0),
            0.50
        )
        vm.addMutable3dObject(cube)

        glSurfaceView.setRenderer(render)
//        layuot.setOnTouchListener(this)
        setListener()

//        setContentView(glSurfaceView)
    }

    override fun onPause() {
        super.onPause()
        glSurfaceView.onPause()
    }

    override fun onResume() {
        super.onResume()
        glSurfaceView.onResume()
    }

    private fun supportES2(): Boolean {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val configurationInfo = activityManager.deviceConfigurationInfo
        return configurationInfo.reqGlEsVersion >= 0x20000
    }

    fun setListener() {
        turnX.setOnClickListener {
            vm.animationRotate3dObject(cube,2000,60,cube.getCenter(),Vector3d(90.0,0.0,-0.0))
//            vm.move3dObject(cube,2000,60,Vector3D(0.0,1.0,0.0))

        }
//        turnUp.setOnClickListener {
//            vm.animationRotate3dObject(cube,2000,60,cube.getCenter(),Vector3D(0.0,-90.0,-0.0))
////            vm.move3dObject(cube,2000,60,Vector3D(0.0,-1.0,0.0))
//
//        }
        turnY.setOnClickListener {
            vm.animationRotate3dObject(cube,2000,60,cube.getCenter(),Vector3d(0.0,90.0,0.0))
//            vm.move3dObject(cube,2000,60,Vector3D(0.0,0.0,90.0))

        }
//        turnRight.setOnClickListener {
//            vm.animationRotate3dObject(cube,2000,60,cube.getCenter(), Vector3D(0.0,0.0,-90.0))
////            vm.move3dObject(cube,2000,60,Vector3D(0.0,0.0,-1.0))
//
//        }
        turnZ.setOnClickListener {
            vm.animationRotate3dObject(cube,2000,60,cube.getCenter(),Vector3d(0.0,0.0,90.0))

        }
        moveX.setOnClickListener {
            vm.animationMove3dObject(cube,2000,60,Vector3d(1.0,0.0,0.0))

        }
        moveY.setOnClickListener {
            vm.animationMove3dObject(cube,2000,60,Vector3d(0.0,1.0,0.0))

        }
        moveZ.setOnClickListener {
            vm.animationMove3dObject(cube,2000,60,Vector3d(0.0,0.0,1.0))

        }
    }

}
