package com.example.rubikscube.cube_fragment;

import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Color;
import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Vector3d;
import com.example.glsurfaceviewvodel.library_for_opengl.primitive.RenderPrimitive;
import com.example.glsurfaceviewvodel.library_for_opengl.primitive.object3d.Cube;
import com.example.glsurfaceviewvodel.library_for_opengl.primitive.object3d.ThreeDimensionalMutableFigure;
import com.example.rubikscube.Direction;
import com.example.rubikscube.cube_fragment.three_dimensional_figure.CubeForRubik;

import org.apache.commons.math3.geometry.euclidean.threed.Line;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RubikCube extends ThreeDimensionalMutableFigure {
   private List<List<List<CubeForRubik>>> listCubes;//list z -> list y -> list x

    private CubeForRubik selectCube;
    private int[] coordinateSelectCube;
    private int size;

    RubikCube(List<List<List<CubeForRubik>>> listCubes, int size) {
        this.listCubes = listCubes;
        this.size = size;
        coordinateSelectCube = new int[3];
        for (int i = 0; i < 3; i++) {
            coordinateSelectCube[i] = 0;
        }
        setSelectCube(new int[]{0, 0, 0});//Z>>Y>>X
    }


    public Cube getSelectCube() {
        return selectCube;
    }

    public int[] getCoordinateSelectCube() {
        return coordinateSelectCube;
    }

    Vector3d getCenter() {
        return new Vector3d(
                new Line(get(0, 0, 0).getCenter(), get(listCubes.size() - 1, listCubes.get(0).size() - 1, listCubes.get(0).get(0).size() - 1).getCenter())
                        .intersection(new Line(get(0, listCubes.get(0).size() - 1, 0).getCenter(), get(listCubes.size() - 1, 0, listCubes.get(0).get(0).size() - 1).getCenter())));
    }

   private Cube get(int x, int y, int z) {
        return listCubes.get(z).get(y).get(x);
    }

    List<List<CubeForRubik>> getBrinkYx(int z) {
        return listCubes.get(z);
    }

    List<List<CubeForRubik>> getBrinkZy(int x) {
        List<List<CubeForRubik>> listResult = new LinkedList();
        for (int i = 0; i < listCubes.size(); i++) {
            List<CubeForRubik> listTmp = new LinkedList();
            for (int j = 0; j < listCubes.get(i).size(); j++) {
                listTmp.add(listCubes.get(i).get(j).get(x));
            }
            listResult.add(listTmp);
        }
        return listResult;
    }

    List<List<CubeForRubik>> getBrinkZx(int y) {
        List<List<CubeForRubik>> listResult = new LinkedList();
        for (int i = 0; i < listCubes.size(); i++) {
            List<CubeForRubik> listTmp = new LinkedList();
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
        for (List<List<CubeForRubik>> list2d : listCubes) {
            for (List<CubeForRubik> list1d : list2d) {
                for (CubeForRubik it : list1d) {
                    list.addAll(it.getPrimitives());
                }
            }
        }
        return list;
    }

    //    public CubeForRubik getSelectCube() {
//        return selectCube;
//    }
//
//    public void setSelectCube(CubeForRubik selectCube) {
//        this.selectCube = selectCube;
//    }

   public void setSelectCube(int[] coordinateSelectCubeNew) {
        if (selectCube != null)
            selectCube.setLineColor(Color.Companion.getBLACK());
        selectCube = listCubes.get(coordinateSelectCubeNew[0]).get(coordinateSelectCubeNew[1]).get(coordinateSelectCubeNew[2]);
        coordinateSelectCube = coordinateSelectCubeNew;
        selectCube.setLineColor(Color.Companion.getORANGE());
    }

    List<com.example.glsurfaceviewvodel.library_for_opengl.primitive.object2d.Line> getLine() {
        List<com.example.glsurfaceviewvodel.library_for_opengl.primitive.object2d.Line> list = new LinkedList<>();
        for (List<List<CubeForRubik>> list2d : listCubes) {
            for (List<CubeForRubik> list1d : list2d) {
                for (CubeForRubik it : list1d) {
                    list.addAll(it.getLine());
                }
            }
        }
        return list;
    }

    public void moveSelectCube(Direction direction) {
        switch (direction) {
            case LEFT:
                coordinateSelectCube[1]++;
                setSelectCube(coordinateSelectCube);
                break;
            case RIGHT:
                coordinateSelectCube[1]--;
                setSelectCube(coordinateSelectCube);
                break;
            case FRONT:
                coordinateSelectCube[0]++;
                setSelectCube(coordinateSelectCube);
                break;
            case BEHEAD:
                coordinateSelectCube[0]--;
                setSelectCube(coordinateSelectCube);
                break;
            case TOP:
                coordinateSelectCube[2]++;
                setSelectCube(coordinateSelectCube);
                break;
            case BOTTOM:
                coordinateSelectCube[2]--;
                setSelectCube(coordinateSelectCube);
                break;
        }
    }


   public boolean isSelectCubeEdge() {
        for (int i = 0; i < 3; i++) {
            if (coordinateSelectCube[i] == 0)
                return true;
        }
        if (coordinateSelectCube[0] == listCubes.size() || coordinateSelectCube[1] == listCubes.get(coordinateSelectCube[0]).size() || coordinateSelectCube[2] == listCubes.get(coordinateSelectCube[0]).get(coordinateSelectCube[1]).size())
            return true;
        return false;
    }

    public ArrayList<Direction> getDirectionSelectCube(Direction direction) {
        ArrayList<Direction> list = new ArrayList();
        if (coordinateSelectCube[0] == 0)
            list.add(Direction.rotationDirection(direction,Direction.BOTTOM));
        else if (coordinateSelectCube[0] == listCubes.size()-1)
            list.add(Direction.rotationDirection(direction,Direction.TOP));

        if (coordinateSelectCube[1] == 0)
            list.add(Direction.rotationDirection(direction,Direction.RIGHT));
        else if (coordinateSelectCube[1] == listCubes.size()-1)
            list.add(Direction.rotationDirection(direction,Direction.LEFT));

        if (coordinateSelectCube[2] == 0)
            list.add(Direction.rotationDirection(direction,Direction.BEHEAD));
        else if (coordinateSelectCube[2] == listCubes.size()-1)
            list.add(Direction.rotationDirection(direction,Direction.FRONT));
            return list;
    }

}
