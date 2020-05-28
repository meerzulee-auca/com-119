package com.meerzulee.model;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class State  {
    private Point2D player;
    private ArrayList<Point2D> boxes;

    public State(Point2D player, ArrayList<Point2D> boxes) {
        this.player = (Point2D) player.clone();
        this.boxes = boxes.stream()
                .map(p -> ((Point2D) p.clone()))
//                convert stream to arraylist
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Point2D getPlayer() {
        return player;
    }

    public ArrayList<Point2D> getBoxes() {
        return boxes;
    }

}
