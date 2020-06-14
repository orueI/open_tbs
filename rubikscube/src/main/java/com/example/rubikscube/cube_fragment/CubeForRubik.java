package com.example.rubikscube.cube_fragment;

import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Vector3d;
import com.example.glsurfaceviewvodel.library_for_opengl.primitive.object2d.Rectangle;
import com.example.glsurfaceviewvodel.library_for_opengl.primitive.object3d.Cube;

import org.jetbrains.annotations.NotNull;

public class CubeForRubik extends Cube {
    public CubeForRubik(@NotNull Rectangle topBrink, @NotNull Rectangle bottomBrink, @NotNull Rectangle leftBrink, @NotNull Rectangle rightBrink, @NotNull Rectangle frontBrink, @NotNull Rectangle behindBrink) {
        super(topBrink, bottomBrink, leftBrink, rightBrink, frontBrink, behindBrink);
    }

    public CubeForRubik(@NotNull Vector3d p, double width) {
        super(p, width);
    }
}
