package com.example.rubikscube.cube_fragment;

import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Color;
import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Vector3d;
import com.example.rubikscube.cube_fragment.three_dimensional_figure.CubeForRubik;

import java.util.LinkedList;
import java.util.List;

public class FactoryCube {

    Color white = new Color(0.9f, 0.9f, 0.9f, 0.9f);
    Color red = new Color(1, 0, 0, 1);
    Color blue = new Color(0, 0, 1, 1);
    Color green = new Color(0, 1, 0, 1);
    Color yellow = new Color(1f, 1f, 0f, 1f);
    Color orange = new Color(0.94f, 0.59f, 0f, 1f);
    private static final FactoryCube ourInstance = new FactoryCube();

    public static FactoryCube getInstance() {
        return ourInstance;
    }

    private FactoryCube() {
    }
    List<List<List<CubeForRubik>>> listCubes = new LinkedList();
    int size;
   public RubikCube createCubeNxN(int n) {
        this.size = n;
        double center = n / 2;
        List<List<List<CubeForRubik>>> listCubes = new LinkedList();
        for (int z = 5; z < n + 5; z++) {
            List<List<CubeForRubik>> list2dTmp = new LinkedList<>();
            for (int y = 5; y < n + 5; y++) {
                List<CubeForRubik> list1dTmp = new LinkedList<>();
                for (int x = 5; x < n + 5; x++) {
                    list1dTmp.add(new CubeForRubik(new Vector3d(x, y, z), 1));
                }
                list2dTmp.add(list1dTmp);
            }
            listCubes.add(list2dTmp);
        }
        RubikCube rubikCube = new RubikCube(listCubes, n);

        drawBrink(rubikCube.getBrinkYx(0), (CubeForRubik cube) -> cube.getBehindBrink().setColor(red));
        drawBrink(rubikCube.getBrinkYx(listCubes.size()-1), (CubeForRubik cube) -> cube.getFrontBrink().setColor(orange));
        drawBrink(rubikCube.getBrinkZx(listCubes.get(0).size() - 1), (CubeForRubik cube) -> cube.getTopBrink().setColor(blue));
        drawBrink(rubikCube.getBrinkZx(0), (CubeForRubik cube) -> cube.getBottomBrink().setColor(green));
        drawBrink(rubikCube.getBrinkZy(0), (CubeForRubik cube) -> cube.getLeftBrink().setColor(white));
        drawBrink(rubikCube.getBrinkZy(listCubes.get(0).get(0).size() - 1), (CubeForRubik cube) -> cube.getRightBrink().setColor(yellow));
        return rubikCube;
    }

    List<List<CubeForRubik>> drawBrink(List<List<CubeForRubik>> brink, InterfaceDrawCube draw) {
        for (List<CubeForRubik> list : brink) {
            for (CubeForRubik it : list) {
                draw.draw(it);
            }
        }
        return brink;
    }

}
