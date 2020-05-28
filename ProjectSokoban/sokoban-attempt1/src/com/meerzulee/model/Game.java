package com.meerzulee.model;

import com.meerzulee.data.Data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Observable;

public class Game extends Observable implements Serializable {
    private HashMap<Integer, Map> maps;
    private HashMap<Integer, Integer> highscore;
    private HashMap<Integer, Integer> currentNumberMoves;
    private HashMap<Integer, String> levelNames;
    private int currentMap;
    private String currentFilename;




    public Game(String path,String filename){
        maps = new HashMap<>();
        highscore = new HashMap<>();
        currentNumberMoves = new HashMap<>();
        levelNames = new HashMap<>();
        currentMap = 0;
        currentFilename = filename;



        if ((path+filename).equals("null")) {
            return;
        }
//        check for highscore
        highscore = Data.loadHighscore(filename);
        if (highscore == null) {
            highscore = new HashMap<>();
        }

        //parser
        try {
            BufferedReader in = new BufferedReader(new FileReader(path+filename));

            String line = "";
            int i = 0;

            while (line != null) {
                ArrayList<String> levels = new ArrayList<>();
                String levelName = "";
                while ((line = in.readLine()) != null && !line.startsWith(";")) {
                    if(line.length() > 0){
                        levels.add(line);
                    }
                }

                if (line !=null ){
                    Map map = new Map(levels);
                    if (line.startsWith(";")){
                        String name = line.split(" ")[1];
                        if (line.split(" ").length >2){
                            levelName = name.concat(" "+line.split(" ")[2]);

                        }
                        map.setLevelName(levelName);
                        levelNames.put(i, levelName);
                    }
                    maps.put(i, map);
                }


                i++;

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        for (int i=0; i<maps.size(); i++) {
            currentNumberMoves.put(i, 0);
        }

    }
    public String getLevelName(int i ) {
        return levelNames.get(i);
    }
    public Map getCurrentMap(){
        //HashMap get current map
        return maps.get(currentMap);
    }
    public int getNMoves(){
        return currentNumberMoves.get(currentMap);
    }
    public int getCurrentMapNumber(){
        return currentMap;
    }
    public String getCurrentMapHighscore(){
//        int hash = getCurrentMap().getMap().hashCode();
        if (highscore.containsKey(currentMap)) {
            return highscore.get(currentMap).toString();
        } else {
            return "-";
        }
    }
    public int getMapsSize(){
        return maps.size();
    }


    //    move(direction)
    public void move(char c){
//        Map.move()
        if (maps.get(currentMap).move(c)) {
//            Is level completed && not last
            if (maps.get(currentMap).isLevelCompleted() && currentMap < maps.size() - 1) {

                //save new highscore if its better than previous
//                int hash = getCurrentMap().getMap().hashCode();
                int nMoves = currentNumberMoves.get(currentMap);
                if (!highscore.containsKey(currentMap) || nMoves < highscore.get(currentMap)) {
                    highscore.put(currentMap, nMoves);
                    Data.saveHighscore(highscore,currentFilename);
                }
                reset();
                currentMap++;
            }
//            increment totalMoves
            else currentNumberMoves.put(currentMap, currentNumberMoves.get(currentMap) + 1);
            setUpdated();
        }
    }

    public void undo(){
//        triggering Map.undo()
        if(maps.get(currentMap).undo()) {
//            decrease totalMoves
            currentNumberMoves.put(currentMap, currentNumberMoves.get(currentMap) - 1);
            setUpdated();
        }
    }
//    TODO Map.redo()
    public void reset(){
//        Map.reset()
        if (maps.get(currentMap).reset()) {
            currentNumberMoves.put(currentMap, 0);
            setUpdated();
        }
    }
    public void nextMap(){
        if (maps.size() - 1 > currentMap) {
            reset();
            currentMap++;
            setUpdated();
        }
    }

    public void prevMap(){
        if (currentMap > 0){
            reset();
            currentMap--;
            setUpdated();
        }
    }

    public void setUpdated(){
        setChanged();
        notifyObservers();
    }

}
