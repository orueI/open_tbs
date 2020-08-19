package com.example.rubikscube;

import org.junit.Test;
import com.example.rubikscube.Direction;
import static org.junit.Assert.assertEquals;

public class Direction_RotationUnit_Test {

    @Test
    public void rotationDirection_isCorrectWithoutRotation() {
        assertEquals(Direction.rotationDirection(Direction.FRONT,Direction.LEFT), Direction.LEFT);
    }

    @Test
    public void rotationDirection_isCorrectWithRotationLeft() {
        assertEquals(Direction.rotationDirection(Direction.LEFT,Direction.LEFT), Direction.BEHEAD);
    }

    @Test
    public void rotationDirection_isCorrectWithRotationRight() {
        assertEquals(Direction.rotationDirection(Direction.RIGHT,Direction.LEFT), Direction.FRONT);
    }

    @Test
    public void rotationDirection_isCorrectWithRotationTop() {
        assertEquals(Direction.rotationDirection(Direction.TOP,Direction.TOP), Direction.BEHEAD);
    }

    @Test
    public void rotationDirection_isCorrectWithRotationBottom() {
        assertEquals(Direction.rotationDirection(Direction.BOTTOM,Direction.TOP), Direction.FRONT);
    }

}
