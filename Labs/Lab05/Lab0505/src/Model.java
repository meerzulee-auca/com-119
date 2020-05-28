public class Model {
    int row;
    int col;

    public void moveRight() {
        if (this.col < 7) {
            this.col++;
        }
    }

    public void moveLeft() {
        if (this.col > 0) {
            this.col--;
        }
    }

    public void moveUp() {
        if (this.row > 0) {
            this.row--;
        }
    }

    public void moveDown() {
        if (this.row < 7) {
            this.row++;
        }
    }
}
