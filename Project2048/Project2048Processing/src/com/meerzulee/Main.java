package com.meerzulee;

import processing.core.PApplet;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static com.meerzulee.Board.BOARD_SIZE;

public class Main extends PApplet {


    private int maximumValue;
    public static PApplet processing;


    public static final int GAME_SIZE = 400;
    public static final int CELL_SIZE = 64;

    public static void main(String[] args) {
        PApplet.main("com.meerzulee.Main", args);
    }

    Board game = new Board();



    public void settings() {
        size(400, 400);
    }

    public void setup() {
        processing = this;
        frameRate(10f);

        reset();
        draw();
    }

    public void draw() {
        int w = width / 4;
        int h = height / 4;
        for (int y = 0; y < game.getBoardHeight(); y++) {
            for (int x = 0; x < game.getBoardWidth(); x++) {

                switch (game.getNumber(x, y)) {
                    case 0:
                        fill(205, 192, 180);
                        break;
                    case 2:
                        fill(238, 228, 218);
                        break;
                    case 4:
                        fill(237, 224, 200);
                        break;
                    case 8:
                        fill(242, 177, 121);
                        break;
                    case 16:
                        fill(245, 149, 99);
                        break;
                    case 32:
                        fill(246, 124, 95);
                        break;
                    case 64:
                        fill(246, 94, 59);
                        break;
                    case 128:
                        fill(237, 207, 114);
                        break;
                    case 256:
                        fill(237, 204, 97);
                        break;
                    case 512:
                        fill(237, 200, 80);
                        break;
                    case 1024:
                        fill(237, 197, 63);
                        break;
                    case 2048:
                        fill(237, 194, 46);
                        break;

                }


                rect(x * w, y * h, width, height);
                if (game.getNumber(x, y) != 0) {
                    fill(0);
                }
                text(game.getNumber(x, y), x * w + w / 2, y * h + h / 2);
                textSize(30);
                textAlign(CENTER, CENTER);
                stroke(187, 173, 160);
            }

        }

        // Show the GAME OVER screen
        if (game.isGameOver()) {
            fill(255, 150, 150, 240);
            rect(0, 0, width, height);
            fill(80);
            text("Game Over!", width / 2, 50);
            text("Your score: " + game.getScore(), width / 2, 100);
            textSize(20);
            fill(0);
            text("Press ENTER to start over", width / 2, height - 50);
        }
    }

    void reset() {
        game.resetGame();

    }

    public void keyPressed() {

        switch (keyCode) {
            case DOWN:
                game.shift(Board.DOWN);
//                draw();
                break;
            case UP:
                game.shift(Board.UP);
//                draw();
                break;
            case RIGHT:
                game.shift(Board.RIGHT);
//                draw();
                break;
            case LEFT:
                game.shift(Board.LEFT);

                break;
            case ENTER:
                if (game.isGameOver()) {
                   reset();
                }
        }


    }



}
