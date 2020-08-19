package com.example.rubikscube;

import com.example.rubikscube.cube_fragment.FactoryCube;
import com.example.rubikscube.cube_fragment.RubikCube;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;


public class RubikCube_GetDirectionSelectCube_Test {
    private RubikCube cube;
    private int n = 2;
    @Before
    public void createRubikCube() {
        FactoryCube factoryCube = FactoryCube.getInstance();
        cube = factoryCube.createCubeNxN(n);
    }

    @Test
    public void getDirectionSelectCube_isCorrectWithZ0Y0X0() {
        cube.setSelectCube(new int[]{0,0,0});

        ArrayList result = new ArrayList();
        result.add(Direction.BOTTOM);
        result.add(Direction.RIGHT);
        result.add(Direction.BEHEAD);
        assertEquals(cube.getDirectionSelectCube(Direction.FRONT), result);
    }

    @Test
    public void getDirectionSelectCube_isCorrectWithZ0Y0X1() {
        cube.setSelectCube(new int[]{0,0,n-1});

        ArrayList result = new ArrayList();
        result.add(Direction.BOTTOM);
        result.add(Direction.RIGHT);
        result.add(Direction.FRONT);
        assertEquals(cube.getDirectionSelectCube(Direction.FRONT), result);
    }

    @Test
    public void getDirectionSelectCube_isCorrectWithZ1Y1X1() {
        cube.setSelectCube(new int[]{n-1,n-1,n-1});

        ArrayList result = new ArrayList();
        result.add(Direction.TOP);
        result.add(Direction.LEFT);
        result.add(Direction.FRONT);
        assertEquals(cube.getDirectionSelectCube(Direction.FRONT), result);
    }

}
