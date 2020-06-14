package com.example.rubikscube.cube_fragment;

import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Vector3d;
import com.example.glsurfaceviewvodel.library_for_opengl.primitive.RenderPrimitive;
import com.example.glsurfaceviewvodel.library_for_opengl.primitive.object3d.Cube;
import com.example.glsurfaceviewvodel.library_for_opengl.primitive.object3d.ThreeDimensionalMutableFigure;

import org.apache.commons.math3.geometry.euclidean.threed.Line;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

public class RubikCube extends ThreeDimensionalMutableFigure {
    List<List<List<Cube>>> listCubes;//list z -> list y -> list x

    Cube selectCube;
    private int[] coordinateSelectCube;
    private int size;

    RubikCube(List<List<List<Cube>>> listCubes, int size) {
        this.listCubes = listCubes;
        this.size = size;
        selectCube = listCubes.get(0).get(0).get(0);
        coordinateSelectCube = new int[3];
        for (int i = 0; i < 3; i++) {
            coordinateSelectCube[i] = 0;
        }
    }

    boolean isSelectCubeEdge() {
        for (int i = 0; i < 3; i++) {
            if (coordinateSelectCube[i] == 0)
                return true;
        }
        if (coordinateSelectCube[0] == listCubes.size() || coordinateSelectCube[1] == listCubes.get(coordinateSelectCube[0]).size() || coordinateSelectCube[2] == listCubes.get(coordinateSelectCube[0]).get(coordinateSelectCube[1]).size())
            return true;
        return false;
    }

    Vector3d getCenter(){
        return new Vector3d(
        new Line(get(0,0,0).getCenter(),get(listCubes.size()-1,listCubes.get(0).size()-1,listCubes.get(0).get(0).size()-1).getCenter())
                .intersection(new Line(get(0,listCubes.get(0).size()-1,0).getCenter(), get(listCubes.size()-1,0,listCubes.get(0).get(0).size()-1).getCenter())));
    }

    Cube get(int x,int y,int z){
        return listCubes.get(z).get(y).get(x);
    }

    List<List<Cube>> getBrinkYx(int z) {
        return listCubes.get(z);
    }

    List<List<Cube>> getBrinkZy(int x) {
        List<List<Cube>> listResult = new LinkedList();
        for (int i = 0; i < listCubes.size(); i++) {
            List<Cube> listTmp = new LinkedList();
            for (int j = 0; j < listCubes.get(i).size(); j++) {
                listTmp.add(listCubes.get(i).get(j).get(x));
            }
            listResult.add(listTmp);
        }
        return listResult;
    }

    List<List<Cube>> getBrinkZx(int y) {
        List<List<Cube>> listResult = new LinkedList();
        for (int i = 0; i < listCubes.size(); i++) {
            List<Cube> listTmp = new LinkedList();
            for (int j = 0; j < listCubes.get(i).get(y).size(); j++) {
                listTmp.add(listCubes.get(i).get(y).get(j));
            }
            listResult.add(listTmp);
        }
        return listResult;
    }

    @Override
    public void move(@NotNull Vector3d vector3D) {
    }

    @Override
    public void turn(@NotNull Vector3d center, @NotNull Vector3d angle) {
    }

    @NotNull
    @Override
    public List<RenderPrimitive> getPrimitives() {
        List<RenderPrimitive> list = new LinkedList<>();
        for (List<List<Cube>> list2d : listCubes) {
            for (List<Cube> list1d : list2d) {
                for (Cube it : list1d) {
                    list.addAll(it.getPrimitives());
                }
            }
        }
        return list;
    }
}
