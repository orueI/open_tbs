package com.example.rubikscube.cube_fragment;

import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Color;
import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Vector3d;
import com.example.glsurfaceviewvodel.library_for_opengl.primitive.object3d.Cube;

import java.util.ArrayList;
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
    List<List<List<Cube>>> listCubes = new LinkedList();
    int size;
    RubikCube createCube2x2(int size) {
        this.size = size;
        double center = size / 2;
        List<List<List<Cube>>> listCubes = new LinkedList();
        for (int z = 0; z < size; z++) {
            List<List<Cube>> list2dTmp = new LinkedList<>();
            for (int y = 0; y < size; y++) {
                List<Cube> list1dTmp = new LinkedList<>();
                for (int x = 0; x < size; x++) {
                    list1dTmp.add(new Cube(new Vector3d(x, y, z), 1));
                }
                list2dTmp.add(list1dTmp);
            }
            listCubes.add(list2dTmp);
        }
        RubikCube rubikCube = new RubikCube(listCubes, size);

//        Cube c00 = new Cube(new Vector3d(-1, 0, 0), 1);
//        Cube c01 = new Cube(new Vector3d(-1, 1, 0), 1);
//        Cube c10 = new Cube(new Vector3d(0, 0, 0), 1);
//        Cube c11 = new Cube(new Vector3d(0, 1, 0), 1);

//        c00.getFrontBrink().setColor(red);
//        c01.getFrontBrink().setColor(red);

//        List<List<Cube>> list2dTmp = new LinkedList<>();
//        List<Cube> list1dTmp = new LinkedList();
//        list1dTmp.add(c00);
//        list1dTmp.add(c01);
//        list1dTmp.add(c10);
//        list1dTmp.add(c11);
//        list2dTmp.add(list1dTmp);
//        listCubes.add(list2dTmp);

//        RubikCube rubikCube = new RubikCube(listCubes, size);
        drawBrink(rubikCube.getBrinkYx(0), (Cube cube) -> cube.getBehindBrink().setColor(red));
        drawBrink(rubikCube.getBrinkYx(listCubes.size()-1), (Cube cube) -> cube.getFrontBrink().setColor(orange));
        drawBrink(rubikCube.getBrinkZx(listCubes.get(0).size() - 1), (Cube cube) -> cube.getTopBrink().setColor(blue));
        drawBrink(rubikCube.getBrinkZx(0), (Cube cube) -> cube.getBottomBrink().setColor(green));
        drawBrink(rubikCube.getBrinkZy(0), (Cube cube) -> cube.getLeftBrink().setColor(white));
        drawBrink(rubikCube.getBrinkZy(listCubes.get(0).get(0).size() - 1), (Cube cube) -> cube.getRightBrink().setColor(yellow));
//        drawBrink(rubikCube.getBrinkYx(size - 1), (Cube cube) -> cube.getTopBrink().setColor(yellow));
//        drawBrink(rubikCube.getBrinkZy(size - 1), (Cube cube) -> cube.getRightBrink().setColor(white));
        return rubikCube;
    }

    List<List<Cube>> drawBrink(List<List<Cube>> brink, InterfaceDrawCube draw) {
        for (List<Cube> list : brink) {
            for (Cube it : list) {
                draw.draw(it);
            }
        }
        return brink;
    }


//    List<List<Cube>> getBrinkZy(int x){
//        List<List<Cube>> listResult = new LinkedList();
//        for (int i = 0; i < size; i++) {
//            List<Cube> listTmp= new LinkedList();
//            for (int j = 0; j < size; j++) {
//                listTmp.add(listCubes.get(i).get(i).get(x));
//            }
//            listResult.add(listTmp);
//        }
//        return listResult;
//    }
//    List<List<Cube>> getBrinkZx(int y){
//        List<List<Cube>> listResult = new LinkedList();
//        for (int i = 0; i < size; i++) {
//            List<Cube> listTmp= new LinkedList();
//            for (int j = 0; j < size; j++) {
//                listTmp.add(listCubes.get(i).get(y).get(i));
//            }
//            listResult.add(listTmp);
//        }
//        return listResult;
//    }

}
