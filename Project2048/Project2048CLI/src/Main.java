import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Board board = new Board();
        BoardView console = new BoardViewConsole(board);

        while (sc.hasNextLine()){
            String command = sc.nextLine().toLowerCase();
            switch (command){
                case "up": board.shift(Board.UP); break;
                case "down": board.shift(Board.DOWN); break;
                case "left": board.shift(Board.LEFT); break;
                case "right": board.shift(Board.RIGHT); break;
                case "present": break;
                default: continue;
            }
            console.present();
        }

    }
}
