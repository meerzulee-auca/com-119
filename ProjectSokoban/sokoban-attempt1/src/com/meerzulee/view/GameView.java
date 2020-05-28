package com.meerzulee.view;


import com.meerzulee.Main;
import com.meerzulee.model.Game;
import com.meerzulee.model.Map;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.geom.Point2D;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Meerzulee on 20/05/2020.
 */
public class GameView implements Observer {

    private Stage stage;
    private Scene scene;
    private AnchorPane pane;
    private Game game;
    private int width = 1400;
    private int heigth = 900;


    public GameView() {

        game = Main.game;


        game.addObserver(this);

        stage = new Stage();
        stage.setWidth(1500);
        stage.setHeight(900);
        stage.setResizable(false);

        stage.show();


        pane = new AnchorPane();
        pane.setStyle("-fx-background-color: #f8f8f8;");

        scene = new Scene(pane);

        stage.setScene(scene);

        //key handler
        scene.setOnKeyPressed(key -> {

                switch (key.getCode()) {
                    //movement

                    case UP: game.move('U'); break;

                    case DOWN: game.move('D'); break;

                    case LEFT: game.move('L'); break;

                    case RIGHT: game.move('R'); break;

                    //undos
                    case U: game.undo(); break;
                    //reset
                    case R: game.reset(); break;

                    //next map
                    case M: game.nextMap(); break;

                    //prev map
                    case N: game.prevMap(); break;
//                    esc
                    case ESCAPE: stage.close(); break;
                }
        });

        drawMap();
        drawInfo();
    }

    public void drawMap(){

        pane.getChildren().clear();

        int x = 0;
        int y = 0;
        int xStep = 50;
        int yStep = 50;

        Map m = game.getCurrentMap();

        int mapWidth = m.getMapWidth();
        int mapHeigth = m.getMap().size();

        int xBegin = (width - mapWidth * 50) / (2 * 50) - 1;
        int yBegin = (heigth - mapHeigth * 50) / (2 * 50);

        //map
        for (String s: m.getMap()) {
            x = -1;
            for (char c : s.toCharArray()) {
                x++;
                String file;

                switch (c){
                    case '#': file = "file:resources/wall.png"; break;
                    case ' ': file = "file:resources/floor.png"; break;
                    case '.': file = "file:resources/goal.png"; break;

                    default: continue;
                }

                ImageView img = new ImageView(new Image(file));
                img.relocate((x + xBegin) * xStep, (y + yBegin) * yStep);
                pane.getChildren().add(img);
            }
            y++;
        }

        //player
        ImageView player = new ImageView(new Image("file:resources/player" + m.getPlayerDirection() + ".png"));
        Point2D p = m.getPlayer();
        player.relocate((p.getX() + xBegin) * xStep, ( p.getY() + yBegin) * yStep);
        pane.getChildren().add(player);



        //boxes
        for (Point2D b: m.getBoxes()){
            String name = "file:resources/box.png";
            switch (game.getCurrentMap().charAt(b)){
                case '.': name = "file:resources/boxO.png"; break;
            }

            ImageView box = new ImageView(new Image(name));
            box.relocate((b.getX() + xBegin) * xStep, (b.getY() + yBegin) * yStep);
            pane.getChildren().add(box);
        }
    }

    public void drawInfo(){


        Button  btnPrev = new Button("<< prev");
        btnPrev.setFont(new Font("Consolas", 20));
        btnPrev.setFocusTraversable(false);
        btnPrev.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                game.prevMap();
            }
        });

        Button  btnNext = new Button("next >>");
        btnNext.setFont(new Font("Consolas", 20));
        btnNext.setFocusTraversable(false);
        btnNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                game.nextMap();
            }
        });

        HBox hBox = new HBox(btnPrev,btnNext);
        hBox.relocate(1200,250);

        //number moves
        Label info = new Label( "Map: " + game.getLevelName(game.getCurrentMapNumber()) +
                                "\nMap number: " + (game.getCurrentMapNumber()+1) + " from "+ game.getMapsSize()+
                                "\nNumber moves: " + game.getNMoves() +
                                "\nHighscore: " + game.getCurrentMapHighscore());
        info.setFont(new Font("Consolas", 20));
        info.setTextFill(Color.BLACK);
        info.relocate(1200, 300);

        //Buttons
        Button  btnUndo = new Button("undo");
        btnUndo.setFont(new Font("Consolas", 20));
        btnUndo.setFocusTraversable(false);
        btnUndo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                game.undo();
            }
        });

        Button  btnReset = new Button("reset");
        btnReset.setFont(new Font("Consolas", 20));
        btnReset.setFocusTraversable(false);
        btnReset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                game.reset();
            }
        });

        HBox hBox2 = new HBox(btnUndo,btnReset);
        hBox2.relocate(1200,420);


        //controls
        Label controls = new Label("\nHotkeys:" +
                                    "\nN/M : prev/next level" +
                                    "\nU/R : undo/reset" +
                                    "\nEsc : exit");
        controls.setFont(new Font("Consolas", 20));
        controls.setTextFill(Color.BLACK);
        controls.relocate(1200, 450);




        pane.getChildren().addAll( info, controls,hBox, hBox2);
    }

    @Override
    public void update(Observable o, Object arg) {
        Platform.runLater( () -> {
            drawMap();
            drawInfo();
        });
    }
}
