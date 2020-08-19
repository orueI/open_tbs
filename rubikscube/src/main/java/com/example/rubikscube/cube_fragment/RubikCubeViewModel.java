package com.example.rubikscube.cube_fragment;

import com.example.glsurfaceviewvodel.library_for_opengl.animation.AnimationRotation;
import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Camera;
import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Vector3d;
import com.example.glsurfaceviewvodel.library_for_opengl.viewmodel.animatin_vm.AnimationViewModel;
import com.example.rubikscube.Direction;

import java.util.ArrayList;

public class RubikCubeViewModel extends AnimationViewModel {

    private RubikCube rubikCube;
    private Direction lastMove = Direction.NOTHING;
    private Direction lastMoveSelectCube = Direction.NOTHING;

    public RubikCubeViewModel(RubikCube rubikCube) {
        this.rubikCube = rubikCube;
        addMutable3dObject(rubikCube);
        rubikCube.getCenter();
        Vector3d center = rubikCube.getCenter();
        Camera camera1 = new Camera(center.plus(new Vector3d(0.2, 2.5, 0.01)), center, new Vector3d(0, 1, 0));
//        camera1.setCenterVector3d(center);
//        camera1.setEveVector3d(rubikCube.getCenter().plus(new Vector3d(1, 0, 0)));
//        Camera camera = new Camera(rubikCube.getCenter().plus(new Vector3d(0.0,2.0,0.0)),rubikCube.getCenter(), new Vector3d(0.0,0.0,0.0));
        setCamera(camera1);
    }

    private void moveSelectCube(Direction direction) {
        Direction directionAbsolute = Direction.getDirection(rubikCube.getSelectCube().getCenter(), getCamera().getEveVector3d());
        Direction directionResult = Direction.rotationDirection(directionAbsolute, direction);

        ArrayList<Direction> listDirection = rubikCube.getDirectionSelectCube(Direction.getDirection(rubikCube.getCenter(), getCamera().getEveVector3d()));
        if (listDirection.contains(direction)) {
            if (direction != lastMoveSelectCube) {
                AnimationRotation rotateAnimation = new AnimationRotation(getCamera().getMutableEveVector(), 1000, 60, rubikCube.getCenter(), directionResult.getVector3d().times(new Vector3d(90, 90, 90)), null);
                rotateAnimation.run();
                return;
            }
        }

        rubikCube.moveSelectCube(direction);
//        int[] coordinate = rubikCube.getCoordinateSelectCube();
//        coordinate[2] += directionResult.getVector3d().getX();
//        coordinate[1] += directionResult.getVector3d().getY();
//        coordinate[0] += directionResult.getVector3d().getZ();
//        rubikCube.setSelectCube(coordinate);
    }

    public void moveUpSelectCube() {
        moveSelectCube(Direction.rotationDirection(Direction.getDirection(rubikCube.getCenter(), getCamera().getEveVector3d()), Direction.TOP));
    }

    public void moveDownSelectCube() {
        moveSelectCube(Direction.rotationDirection(Direction.getDirection(rubikCube.getCenter(), getCamera().getEveVector3d()), Direction.BOTTOM));
    }

    public void moveLeftSelectCube() {
        moveSelectCube(Direction.rotationDirection(Direction.getDirection(rubikCube.getCenter(), getCamera().getEveVector3d()), Direction.LEFT));
    }

    void moveRightSelectCube() {
        moveSelectCube(Direction.rotationDirection(Direction.getDirection(rubikCube.getCenter(), getCamera().getEveVector3d()), Direction.RIGHT));
    }
}
