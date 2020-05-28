public class BoardViewConsole extends BoardView {
    public static final String EMPTY_CELL = "0";

    public BoardViewConsole(Board board) {
        super(board);
    }
    @Override
    void present(){
        int columnSize = String.valueOf(board.getMaximumValue()).length()+2;
        String format = "%"+ columnSize + "s";
        if(board.isGameOver() || board.getMaximumValue()==2048){
            for (int y = 0; y < board.getHeight(); y++) {
                if (board.getMaximumValue()==2048){
                    System.out.println("YOU WIN!");
               }else{
                    System.out.println("YOU LOSE!");
                }
        }

        }else {
            for (int y = 0; y < board.getHeight(); y++) {
                for (int x = 0; x < board.getWidth(); x++) {
                    int number = board.getNumber(x,y);
                    System.out.printf(format, number == 0 ? EMPTY_CELL :String.valueOf(number));
                }
                System.out.println();
            }
        }
    }
}
