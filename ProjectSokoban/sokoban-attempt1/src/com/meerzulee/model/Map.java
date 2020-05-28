package com.meerzulee.model;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Map implements Serializable {
    private ArrayList<String> map;
    private ArrayList<String> reversedMap; //because Y is reversed
    private ArrayList<Point2D> boxes;
    private ArrayList<State> history;
    private Point2D player;
    private char playerDirection;
    private String levelName;



    public Map(ArrayList<String> m) {
        map = new ArrayList<>();
        boxes = new ArrayList<>();
        history = new ArrayList<>();
        player = new Point2D.Double();
        playerDirection = 'R';

        ArrayList<Point2D> coords = new ArrayList<>();


         for (int y = 0; y < m.size(); y++) {
            String s = m.get(y);
            if (s.contains("@")){
                int x = s.indexOf("@");
                s = s.replace('@',' ');
                m.set(y, s);
                player.setLocation(x, y);

            }
            if (s.contains("$")){
                //check for duplicated
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == '$') {
                        int x = s.indexOf("$");
                        s = s.replaceFirst("\\$"," ");
                        m.set(y, s);
                        coords.add(new Point2D.Double(x,y));
                    }
                }


            }
            if (s.contains("*")){
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == '*') {
                        int x = s.indexOf("*");
                        s = s.replaceFirst("\\*",".");
                        m.set(y, s);
                        coords.add(new Point2D.Double(x,y));
                    }
                }

            }
             map.add(s);

        }
        //parse coords

        parseCoords(coords);


        reversedMap = (ArrayList<String>) map.clone();
//        Collections.reverse(reversedMap);

    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String leveName) {
        this.levelName = leveName;
//        System.out.println(this.levelName);
    }

    public ArrayList<String> getMap() {
        return map;
    }

    public Point2D getPlayer() {
        return player;
    }

    public ArrayList<Point2D> getBoxes() {
        return boxes;
    }

    public char getPlayerDirection(){
        return playerDirection;
    }

    private void parseCoords(ArrayList<Point2D> coords){
        for (int i=0; i<coords.size(); i++){

                boxes.add(new Point2D.Double(coords.get(i).getX(), coords.get(i).getY()));

        }
    }
    public char charAt(Point2D p){
        return reversedMap.get((int) p.getY()).charAt((int) p.getX());
    }

    private boolean barrier(Point2D p){
        char c = charAt(p);
        if (c == '#' || existsBox(p))
            return true;
        else return false;
    }
    private boolean existsBox(Point2D p){
        for (Point2D coord: boxes)
            if (coord.equals(p))
                return true;
        return false;
    }

    public void moveBox(Point2D p, ArrayList<Point2D> boxes, int x, int y){
        for (Point2D coord: boxes)
            if (coord.equals(p)) {
                coord.setLocation(coord.getX() + x, coord.getY() + y);
                return;
            }
    }

    private void addMove(){
        history.add(new State(player, boxes));
    }

    private void updateDirection(char c){
        playerDirection = c;
    }

    public int getMapWidth(){
        int w = 0;
        for (String s : map){
            if(s.length() > 0){
                w = s.length();
            }
        }
        return w;
    }
    public int getMapHeight(){

        return map.size();
    }

    public boolean move(char c){
        int x = 0;
        int y = 0;

        switch (c){
            case 'U': y = -1; break;
            case 'D': y = 1; break;
            case 'L': x = -1; break;
            case 'R': x = 1; break;
        }

        Point2D pl;
        pl = player;

        int dx = (int) pl.getX() + x;
        int dy = (int) pl.getY() + y;

        Point2D nextPos = new Point2D.Double(dx, dy);
        char nextPosChar = charAt(nextPos);


        //wall
        if (nextPosChar == '#')
            return false;

        //box (nextPos) && box(nextNextPos)
        if (existsBox(nextPos) && barrier(new Point2D.Double(dx + x, dy + y)))
            return false;


        //valid move
        addMove();
        moveBox(new Point2D.Double(dx, dy), boxes, x, y);
        pl.setLocation(dx, dy);
        updateDirection(c);

        return true;
    }
    public boolean undo(){
        int size = history.size();
        if (size > 0) {
            player = history.get(size-1).getPlayer();
            boxes = history.get(size-1).getBoxes();
            history.remove(size-1);
            return true;
        }
        else return false;
    }
//    TODO create class redo()
    public boolean reset(){
        if (history.size() > 0) {
//           get initial position
            player = history.get(0).getPlayer();

            boxes = history.get(0).getBoxes();
            history.clear();

            return true;
        }
        return false;
    }
    public boolean isLevelCompleted(){
        return boxes.stream()
                .filter(b -> charAt(b) == '.')
                .count() == boxes.size();
    }

}
