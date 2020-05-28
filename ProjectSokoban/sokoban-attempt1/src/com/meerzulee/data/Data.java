package com.meerzulee.data;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Meerzulee on 20/05/2020.
 */
public class Data {
    //TODO create an empty "highscore" hashmap
    // Fill it with -1
    // Load data and merge with empty hashmap
    public static void saveHighscore(HashMap<Integer, Integer> highscore,String filename){
//        try {
//            FileOutputStream fos = new FileOutputStream("score.txt");
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject(highscore);
//            oos.flush();
//            oos.close();
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
        String currentMap = filename.split(".txt")[0];
        File result = new File("resources/maps/"+currentMap+".best");
        BufferedWriter bf = null;;
        try{
            if (result.createNewFile()) {
                System.out.println("File created: " + result.getName());
                //create new BufferedWriter for the output file
                bf = new BufferedWriter( new FileWriter(result) );

                //iterate map entries
                for(Map.Entry<Integer, Integer> entry : highscore.entrySet()){

                    //put key and value separated by a colon
                    bf.write(""+  entry.getValue() );

                    //new line
                    bf.newLine();
                }

            }
            //create new BufferedWriter for the output file
            bf = new BufferedWriter( new FileWriter(result) );

            //iterate map entries
            for(Map.Entry<Integer, Integer> entry : highscore.entrySet()){

                //put key and value separated by a colon
                bf.write(""+  entry.getValue() );

                //new line
                bf.newLine();
            }

            bf.flush();
        } catch (Exception e) {
                e.printStackTrace();
        }finally{

            try{
                //always close the writer
                bf.close();
            }catch(Exception e){}
        }

    }

    public static HashMap<Integer, Integer> loadHighscore(String filename){
        HashMap<Integer, Integer> highscore = new HashMap<>();
        BufferedReader br = null;
        String currentMap = filename.split(".txt")[0];
//        System.out.println(currentMap);
        try {

            File result = new File("resources/maps/"+currentMap +".best");


            //create BufferedReader object from the File
            br = new BufferedReader( new FileReader(result) );

            String line = null;
            int i = 0;
            //read file line by line
            while ( (line = br.readLine()) != null ){
                highscore.put(i,Integer.parseInt(line));
//               highscore.put()

            i++;
            }
            System.out.println(highscore);
            return highscore;
        }
        catch (Exception e) {
            System.err.println("No '"+ currentMap +"' found.");
        }
        return null;
    }

}
