package com.example.rubikscube.cube_fragment.three_dimensional_figure;

import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Color;
import com.example.glsurfaceviewvodel.library_for_opengl.dataclass.Vector3d;
import com.example.glsurfaceviewvodel.library_for_opengl.primitive.RenderPrimitive;
import com.example.glsurfaceviewvodel.library_for_opengl.primitive.object2d.Line;
import com.example.glsurfaceviewvodel.library_for_opengl.primitive.object2d.Rectangle;
import com.example.glsurfaceviewvodel.library_for_opengl.primitive.object3d.Cube;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CubeForRubik extends Cube {

    private Color lineColor = Color.Companion.getBLACK();

    public CubeForRubik(@NotNull Rectangle topBrink, @NotNull Rectangle bottomBrink, @NotNull Rectangle leftBrink, @NotNull Rectangle rightBrink, @NotNull Rectangle frontBrink, @NotNull Rectangle behindBrink) {
        super(topBrink, bottomBrink, leftBrink, rightBrink, frontBrink, behindBrink);
    }

    public CubeForRubik(@NotNull Vector3d p, double width) {
        super(p, width);
    }

    @NotNull
    @Override
    public List<RenderPrimitive> getPrimitives() {
        List<RenderPrimitive> list = super.getPrimitives();
        ArrayList<RenderPrimitive> listResult = new ArrayList<>();
        listResult.addAll(list);
//        for (int i = 0; i < list.size(); i++) {
//            listResult.a
//        }

        List listLine = getLine();
        listResult.addAll(listLine);

//        listLine.forEach(((Line line) -> list.add((RenderPrimitive) line)));

//        for (int i = 0; i < listLine.size(); i++) {
////            list.add((RenderPrimitive) listLine.get(i));
////        }

        return listResult;
    }

    public List<Line> getLine() {
        List<Line> list = new LinkedList<>();
        getLineBrink(list, getFrontBrink());
        getLineBrink(list, getBehindBrink());
        getLineConnectBrink(list,getFrontBrink(),getBehindBrink());
        return list;
    }

    private void getLineBrink(List<Line> list, Rectangle brink) {
        list.add(new Line(brink.getPoint1(), brink.getPoint2(), lineColor,3.0));
        list.add(new Line(brink.getPoint1(), brink.getPoint3(), lineColor,3.0));
        list.add(new Line(brink.getPoint4(), brink.getPoint3(), lineColor,3.0));
        list.add(new Line(brink.getPoint4(), brink.getPoint3(), lineColor,3.0));
    }

    private void getLineConnectBrink(List<Line> list, Rectangle brink1, Rectangle brink2) {
        list.add(new Line(brink1.getPoint1(), brink2.getPoint1(), lineColor,3.0));
        list.add(new Line(brink1.getPoint2(), brink2.getPoint2(), lineColor,3.0));
        list.add(new Line(brink1.getPoint3(), brink2.getPoint3(), lineColor,3.0));
        list.add(new Line(brink1.getPoint4(), brink2.getPoint4(), lineColor,3.0));
    }


    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    @NotNull
    @Override
    public Rectangle getTopBrink() {
        return super.getTopBrink();
    }

}
