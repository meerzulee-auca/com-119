package io.meerzulee.game2048;

import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Board extends javafx.scene.canvas.Canvas{
    public static final int BOARD_SIZE = 4;
    private int[][] data;
    private int maximumValue;
    public static final Point LEFT = new Point(-1, 0);
    public static final Point RIGHT = new Point(1, 0);
    public static final Point UP = new Point(0, 1);
    public static final Point DOWN = new Point(0, -1);
    private boolean isChanged = false;
    private boolean isGameOver = false;
    public static final int GAME_SIZE = 400;
    public static final int CELL_SIZE = 64;
    private int score= 0;



    Board() {
        super(400,400);
        data = new int[BOARD_SIZE][BOARD_SIZE];
//        data = new int[][]{
//                {8, 4, 8, 4},
//                {0, 8, 4, 8},
//                {8, 4, 8, 4},
//                {4, 8, 16, 16}
//        };

        maximumValue = 2;
        placeRandomNumbers(2);
    }

    void resetGame(){
        maximumValue = 0;
        isGameOver= false;
        for (int y = 0; y < getBoardHeight(); y++) {
            for (int x = 0; x < getBoardWidth(); x++) {
                data[y][x] = 0;
            }
        }

        placeRandomNumbers(2);
    }
    boolean isGameOver() {
        return isGameOver;
    }
    int getBoardWidth() {
        return data[0].length;
    }

    int getBoardHeight() {
        return data.length;
    }

    int getNumber(int x, int y) {
        return data[y][x];
    }

    int getMaximumValue() {
        return maximumValue;
    }

    public javafx.scene.paint.Color getBackground(int number) {
        switch (number) {
            case 2:		return javafx.scene.paint.Color.rgb(238, 228, 218, 1.0); //238 228 218 1.0      0xeee4da
            case 4: 	return javafx.scene.paint.Color.rgb(237, 224, 200, 1.0); //237, 224, 200, 1.0   0xede0c8
            case 8: 	return javafx.scene.paint.Color.rgb(242, 177, 121, 1.0); //242, 177, 121, 1.0   0xf2b179
            case 16: 	return javafx.scene.paint.Color.rgb(245, 149, 99, 1.0); //245, 149, 99, 1.0     0xf59563
            case 32: 	return javafx.scene.paint.Color.rgb(246, 124, 95, 1.0); //246, 124, 95, 1.0     0xf67c5f
            case 64:	return javafx.scene.paint.Color.rgb(246, 94, 59, 1.0 ); //246, 94, 59, 1.0      0xf65e3b
            case 128:	return javafx.scene.paint.Color.rgb(237, 207, 114, 1.0); //237, 207, 114, 1.0   0xedcf72
            case 256: 	return javafx.scene.paint.Color.rgb(237, 204, 97, 1.0); //237, 204, 97, 1.0     0xedcc61
            case 512: 	return javafx.scene.paint.Color.rgb(237, 200, 80, 1.0); //237, 200, 80, 1.0     0xedc850
            case 1024: 	return javafx.scene.paint.Color.rgb(237, 197, 63, 1.0); //237, 197, 63, 1.0     0xedc53f
            case 2048: 	return javafx.scene.paint.Color.rgb(237, 194, 46, 1.0); //237, 194, 46, 1.0     0xedc22e
        }
        return Color.rgb(205, 193, 180, 1.0); //0xcdc1b4
    }

    public Color getForeground(int number) {
        Color foreground;
        if(number < 16) {
            foreground = Color.rgb(119, 110, 101, 1.0); //0x776e65
        } else {
            foreground = Color.rgb(249, 246, 242, 1.0);    //0xf9f6f2
        }
        return foreground;
    }

    void shift(Point direction) {
        if (direction == LEFT) {
            int[][] temp = copyArray(data);

           swipeLeft();

            if (!Arrays.deepEquals(temp, data)) {
                placeRandomNumbers(2);
            }


        } else if (direction == RIGHT) {
            int[][] temp = copyArray(data);
            rotateMatrixLeft(BOARD_SIZE, data);
            rotateMatrixLeft(BOARD_SIZE, data);

            swipeLeft();

            rotateMatrixLeft(BOARD_SIZE, data);
            rotateMatrixLeft(BOARD_SIZE, data);

            if (!Arrays.deepEquals(temp, data)) {
                placeRandomNumbers(2);
            }
        } else if (direction == UP) {
            int[][] temp = copyArray(data);

            rotateMatrixLeft(BOARD_SIZE, data);

            swipeLeft();

            rotateMatrixRight(BOARD_SIZE, data);

            if (!Arrays.deepEquals(temp, data)) {
                placeRandomNumbers(2);
            }
        } else if (direction == DOWN) {
            int[][] temp = copyArray(data);
            rotateMatrixRight(BOARD_SIZE, data);

            swipeLeft();

            rotateMatrixLeft(BOARD_SIZE, data);

            if (!Arrays.deepEquals(temp, data)) {
                placeRandomNumbers(2);
            }
        }
    }

    boolean hasMove(){
        for (int y = 0; y < getBoardHeight(); y++) {
            for (int x = 0; x < getBoardWidth(); x++) {
                if(data[y][x] ==0){
                    return false;
                }//y != 3; don't check last row or column
                if (y != 3 && data[y][x] == data[y+1][x]){
                    return false;
                }
                if (x != 3 && data[y][x] == data[y][x+1]){
                    return false;
                }
            }
        }
        return true;
    }

    // function that rotates a N x N matrix
    // by 90 degrees in anti-clockwise direction
    void rotateMatrixLeft(int N, int mat[][]) {
        // Consider all squares one by one
        for (int x = 0; x < N / 2; x++) {
            // Consider elements in group of 4 in
            // current square
            for (int y = x; y < N - x - 1; y++) {
                // store current cell in temp variable
                int temp = mat[x][y];

                // move values from right to top
                mat[x][y] = mat[y][N - 1 - x];

                // move values from bottom to right
                mat[y][N - 1 - x] = mat[N - 1 - x][N - 1 - y];

                // move values from left to bottom
                mat[N - 1 - x][N - 1 - y] = mat[N - 1 - y][x];

                // assign temp to left
                mat[N - 1 - y][x] = temp;
            }
        }
    }

    // 90 degrees in clockwise
    void rotateMatrixRight(int N, int mat[][]) {
        // Traverse each cycle
        for (int i = 0; i < N / 2; i++) {
            for (int j = i; j < N - i - 1; j++) {

                // Swap elements of each cycle
                // in clockwise direction
                int temp = mat[i][j];
                mat[i][j] = mat[N - 1 - j][i];
                mat[N - 1 - j][i] = mat[N - 1 - i][N - 1 - j];
                mat[N - 1 - i][N - 1 - j] = mat[j][N - 1 - i];
                mat[j][N - 1 - i] = temp;
            }
        }
    }

    void slideLeft() {
//                {0, 0, 0, 0},
//                {0, 2, 0, 2},
//                {2, 0, 0, 0},
//                {0, 0, 0, 0}
        //DRY - don't repeat yourself
        //KISS - keep it stupid simple
        for (int y = 0; y < getBoardHeight(); y++) {
            ArrayList<Integer> temp = new ArrayList<>();
            //extracting non-zero numbers
            for (int i = 0; i < getBoardWidth(); i++) {
                if (data[y][i] != 0) {
                    temp.add(data[y][i]);//{2,2,}
                }
            }
            //temp {2,2,2,2}
            int arraySize = temp.size(); //length 2
            //calculating how many zeros we should add
            for (int i = 0; i < data[y].length - arraySize; i++) {
                temp.add(0);//{2,2,0,0} =  {0, 2, 0, 2},
            }
            //replacing temp row with  original row
            for (int i = 0; i < temp.size(); i++)
                data[y][i] = temp.get(i);//{2,2,0,0} =  {0, 2, 0, 2},
        }
    }

    void mergeLeft() {
        for (int y = 0; y < getBoardHeight(); y++) {
            for (int i = 1; i < getBoardWidth(); i++) {
                //{4,0}
                int a = data[y][i - 1];
                int b = data[y][i];
                if (a == b) {
                    data[y][i - 1] = a + b;
                    data[y][i] = 0;
                    score = score + (a*2);
                }
            }
        }
    }

    void swipeLeft(){
        slideLeft();
        relocate(GAME_SIZE, GAME_SIZE);
        mergeLeft();
        relocate(GAME_SIZE, GAME_SIZE);
        updateMaxValue();
        slideLeft();
        relocate(GAME_SIZE, GAME_SIZE);
    }

    int[][] copyArray(int[][] data) {
        int[][] temp = new int[getBoardHeight()][getBoardWidth()];
        for (int y = 0; y < getBoardHeight(); y++) {
            for (int x = 0; x < getBoardWidth(); x++) {
                temp[y][x] = data[y][x];
            }
        }
        return temp;
    }

    private void placeRandomNumbers(int numbersToPlace) {
        ArrayList<Point> unoccupiedCoordinates = new ArrayList<>();

        for (int y = 0; y < getBoardHeight(); y++) {
            for (int x = 0; x < getBoardWidth(); x++) {
                if (data[y][x] == 0) {
                    unoccupiedCoordinates.add(new Point(x, y));
                }
            }
        }
        for (int i = 0; i < numbersToPlace && unoccupiedCoordinates.size() > 0; i++) {
            int randomIndex = (int) (Math.random() * unoccupiedCoordinates.size());
            Point point = unoccupiedCoordinates.get(randomIndex);
            unoccupiedCoordinates.remove(randomIndex);
            data[point.y][point.x] = (Math.random() * 2) < 0.9 ? 2 : 4;
            //2 or 4
        }
    }
    private void updateMaxValue(){
        for (int y = 0; y < getBoardHeight(); y++) {
            for (int x = 1; x < getBoardWidth(); x++) {
                if (data[y][x] > maximumValue) {
                    maximumValue = data[y][x];
                }
            }
        }
        isGameOver = hasMove();

    }
    public int getScore() {
        return score;
    }
}
