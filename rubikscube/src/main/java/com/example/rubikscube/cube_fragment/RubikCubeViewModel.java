package com.example.rubikscube.cube_fragment;

import android.graphics.Path;

import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Camera;
import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Vector3d;
import com.example.glsurfaceviewvodel.library_for_opengl.viewmodel.animatin_vm.AnimationViewModel;
import com.example.rubikscube.Direction;

import org.apache.commons.math3.geometry.euclidean.threed.Line;

public class RubikCubeViewModel extends AnimationViewModel {
    public RubikCubeViewModel(RubikCube rubikCube) {
        this.rubikCube = rubikCube;
        addMutable3dObject(rubikCube);
        rubikCube.getCenter();
        Camera camera1 = new Camera();
        camera1.setCenterVector3d(rubikCube.getCenter());
        camera1.setEveVector3d(rubikCube.getCenter().plus(new Vector3d(-2.0,3.0,-2.0)));
//        Camera camera = new Camera(rubikCube.getCenter().plus(new Vector3d(0.0,2.0,0.0)),rubikCube.getCenter(), new Vector3d(0.0,0.0,0.0));
        setCamera(camera1);
    }

    RubikCube rubikCube;

    void moveUpSelectCube() {
        rubikCube.isSelectCubeEdge();

    }

    void moveDownSelectCube() {

    }

    void moveLeftSelectCube() {

    }

    void moveRightSelectCube() {

    }

//    Direction creteDirection(){
//
//    }
}
