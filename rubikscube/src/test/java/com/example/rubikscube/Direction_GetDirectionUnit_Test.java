package com.example.rubikscube;

import org.junit.Test;

import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Vector3d;
import com.example.rubikscube.Direction;
import static org.junit.Assert.assertEquals;

public class Direction_GetDirectionUnit_Test {

    @Test
    public void getDirection_isCorrect() {
        assertEquals(Direction.getDirection(new Vector3d(1,2,3),new Vector3d(1,5,3)),Direction.LEFT);
    }

}
