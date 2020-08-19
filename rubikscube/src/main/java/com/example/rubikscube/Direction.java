package com.example.rubikscube;

import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Vector3d;

import org.apache.commons.math3.geometry.euclidean.threed.Rotation;

public enum Direction {
    LEFT {
        public Vector3d getVector3d() {
            return new Vector3d(0, 1, 0);
        }
    }, RIGHT {
        public Vector3d getVector3d() {
            return new Vector3d(0, -1, 0);
        }
    }, TOP {
        public Vector3d getVector3d() {
            return new Vector3d(0, 0, 1);
        }
    }, BOTTOM {
        public Vector3d getVector3d() {
            return new Vector3d(0, 0, -1);
        }
    }, FRONT {
        public Vector3d getVector3d() {
            return new Vector3d(1, 0, 0);
        }
    }, BEHEAD {
        public Vector3d getVector3d() {
            return new Vector3d(-1, 0, 0);
        }
    }, NOTHING {
        public Vector3d getVector3d() {
            return new Vector3d(0, 0, 0);
        }
    };

    public abstract Vector3d getVector3d();

    public static Direction rotationDirection(Direction base, Direction rotable) {
        if (Direction.FRONT != base) {
            Rotation rotation = new Rotation(new Vector3d(1, 0, 0), base.getVector3d().times(new Vector3d(-1, -1, -1)));
            return Direction.getDirection(new Vector3d(rotation.applyInverseTo(rotable.getVector3d())));
        } else
            return rotable;
    }

    public static Direction getDirection(Vector3d division) {
        if (division.getX() > 0)
            return Direction.FRONT;
        else if (division.getX() < 0)
            return Direction.BEHEAD;
        else if (division.getY() > 0)
            return Direction.LEFT;
        else if (division.getY() < 0)
            return Direction.RIGHT;
        else if (division.getZ() > 0)
            return Direction.TOP;
        else if (division.getZ() < 0)
            return Direction.BOTTOM;
        else
            return Direction.NOTHING;
    }


    public static Direction getDirection(Vector3d center, Vector3d eveVector3d) {
        Vector3d tmp = eveVector3d.div(center);
        Vector3d division = new Vector3d(Math.abs(tmp.getX()), Math.abs(tmp.getY()), Math.abs(tmp.getZ()));
        division = leaveMaxValue(division);
        return getDirection(division);
    }

    private static Vector3d leaveMaxValue(Vector3d vector3d) {
        if (vector3d.getX() > vector3d.getY() && vector3d.getX() > vector3d.getZ()) {
            return new Vector3d(vector3d.getX(), 0, 0);
        } else if (vector3d.getY() > vector3d.getX() && vector3d.getY() > vector3d.getZ()) {
            return new Vector3d(0, vector3d.getY(), 0);
        } else if (vector3d.getZ() > vector3d.getX() && vector3d.getZ() > vector3d.getY()) {
            return new Vector3d(0, 0, vector3d.getZ());
        } else if (vector3d.getZ() == vector3d.getX() && vector3d.getZ() == vector3d.getY()) {
            return new Vector3d(0, 0, 0);
        }
        return new Vector3d(0, 0, 0);
    }
}
