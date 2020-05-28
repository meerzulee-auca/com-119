package com.meerzulee;

import com.meerzulee.model.Game;
import com.meerzulee.view.GameView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

public class Main extends Application {
    public static Game game;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(new URL("file:src/com/meerzulee/view/Menu.fxml"));
        Scene scene = new Scene(root);
        Stage window = new Stage();
        window.setScene(scene);
        window.setTitle("Sokoban 1");
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(true);
        window.show();
        window.setOnCloseRequest(event -> System.exit(0));
    }

    public static void startGame(String path,String filename){
//        String filename = "resources/MiniCosmos.txt";
        game = new Game(path,filename);
        new GameView();
    }
    public static ArrayList<String> getMaps(){
        ArrayList<String> mapsName = new ArrayList<>();
        File folder = new File("resources/maps");
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                try{
                    mapsName.add(file.getName());

                }catch (Exception e){
                    System.err.println(e);
                }

            }
        }
        return mapsName;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
