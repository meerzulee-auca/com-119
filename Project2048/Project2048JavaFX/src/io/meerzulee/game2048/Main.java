package io.meerzulee.game2048;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Game 2048");

        FlowPane rootNode = new FlowPane();

        primaryStage.setOnCloseRequest(event -> Platform.exit());

        Board game = new Board();

        Scene myScene = new Scene(rootNode, game.getWidth() ,game.getHeight());
        primaryStage.setScene(myScene);

        myScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ESCAPE){
                   game.resetGame();
                }

                if (!game.isGameOver()) {
                    switch (event.getCode()) {
                        case LEFT:
                            game.shift(game.LEFT);

                            break;
                        case RIGHT:
                            game.shift(game.RIGHT);
                            break;
                        case DOWN:
                            game.shift(game.DOWN);
                            break;
                        case UP:
                            game.shift(game.UP);

                            break;
                    }
                }
                game.relocate(game.GAME_SIZE, game.GAME_SIZE);
            }
        });
        rootNode.getChildren().add(game);
        primaryStage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.01), actionEvent -> {
            GraphicsContext gc = game.getGraphicsContext2D();
            gc.setFill(Color.rgb(187, 173, 160, 1.0));
            gc.fillRect(0, 0, game.getWidth(), game.getHeight());

            for(int y = 0; y < 4; y++) {
                for(int x = 0; x < 4; x++){

                    int value = game.getNumber(x,y);
                    int xOffset = offsetCoors(x);
                    int yOffset = offsetCoors(y);

                    gc.setFill(game.getBackground(value));
                    gc.fillRoundRect(xOffset, yOffset, game.CELL_SIZE, game.CELL_SIZE , 20, 20);
                    gc.setFill(game.getForeground(value));

                    final int size = value < 100 ? 32 : value < 1000 ? 28 : 24;
                    gc.setFont(Font.font("Verdana", FontWeight.BOLD, size));
                    gc.setTextAlign(TextAlignment.CENTER);


                    String s = String.valueOf(value);

                    if (value != 0)
                        gc.fillText(s, xOffset + game.CELL_SIZE / 2, yOffset + game.CELL_SIZE / 2 + (game.CELL_SIZE/8));
                    if(game.isGameOver()) {
                        gc.setFill(Color.rgb(255, 255, 255));
                        gc.fillRect(0, 0, game.getWidth(), game.getHeight());
                        gc.setFill(Color.rgb(78, 139, 202));
                        gc.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
                        if(game.getMaximumValue()==2048){
                            gc.fillText("You win!", game.GAME_SIZE / 2, 150);
                        }else  {
                            gc.fillText("Game over!", game.GAME_SIZE / 2, 130);
                            gc.fillText("You lose!", game.GAME_SIZE / 2, 200);
                        }
                        if(game.isGameOver()) {
                            gc.setFont(Font.font("Verdana", FontWeight.LIGHT, 16));
                            gc.setFill(Color.rgb(128, 128, 128));
                            gc.fillText("Press ESC to play again", game.GAME_SIZE / 2, 270);
                        }
                    }
                    gc.setFont(Font.font("Verdana", FontWeight.LIGHT, 18));
                    gc.fillText("Score: " + game.getScore(), game.GAME_SIZE / 2, 390);
                }
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private static int offsetCoors(int pos) {
        return pos * (24 + 64) + 32;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
