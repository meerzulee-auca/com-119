import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    public static final int BOARD_SIZE = 4;
    private int[][] data;
    private int maximumValue;
    public static final Point LEFT = new Point(-1, 0);
    public static final Point RIGHT = new Point(1, 0);
    public static final Point UP = new Point(0, 1);
    public static final Point DOWN = new Point(0, -1);
    private boolean isChanged = false;
    private boolean isGameOver = false;


    Board() {
        data = new int[BOARD_SIZE][BOARD_SIZE];
//        data = new int[][]{
//                {8, 4, 8, 4},
//                {4, 8, 4, 8},
//                {8, 4, 8, 4},
//                {4, 8, 16, 16}
//        };
        maximumValue = 2;

        placeRandomNumbers(2);
    }

    boolean isGameOver() {
        return isGameOver;
    }
    int getWidth() {
        return data[0].length;
    }

    int getHeight() {
        return data.length;
    }

    int getNumber(int x, int y) {
        return data[y][x];
    }

    int getMaximumValue() {
        return maximumValue;
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
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
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
        for (int y = 0; y < getHeight(); y++) {
            ArrayList<Integer> temp = new ArrayList<>();
            //extracting non-zero numbers
            for (int i = 0; i < getWidth(); i++) {
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
        for (int y = 0; y < getHeight(); y++) {
            for (int i = 1; i < getWidth(); i++) {
                //{4,0}
                int a = data[y][i - 1];
                int b = data[y][i];
                if (a == b) {
                    data[y][i - 1] = a + b;
                    data[y][i] = 0;
                }
            }
        }
    }

    void swipeLeft(){
        slideLeft();
        mergeLeft();
        updateMaxValue();
        slideLeft();
    }

    int[][] copyArray(int[][] data) {
        int[][] temp = new int[getHeight()][getWidth()];
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                temp[y][x] = data[y][x];
            }
        }
        return temp;
    }

    private void placeRandomNumbers(int numbersToPlace) {
        ArrayList<Point> unoccupiedCoordinates = new ArrayList<>();

        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                if (data[y][x] == 0) {
                    unoccupiedCoordinates.add(new Point(x, y));
                }
            }
        }
        for (int i = 0; i < numbersToPlace && unoccupiedCoordinates.size() > 0; i++) {
            int randomIndex = (int) (Math.random() * unoccupiedCoordinates.size());
            Point point = unoccupiedCoordinates.get(randomIndex);
            unoccupiedCoordinates.remove(randomIndex);
            data[point.y][point.x] = 2;
            //2 or 4
        }
    }
    private void updateMaxValue(){
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 1; x < getWidth(); x++) {
                if (data[y][x] > maximumValue) {
                    maximumValue = data[y][x];
                }
            }
        }
        isGameOver = hasMove();

    }
}
