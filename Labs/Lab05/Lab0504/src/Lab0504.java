import javax.swing.*;
import java.awt.*;

public class Lab0504 extends JFrame{
    CanvasPanel canvasPanel;

    Lab0504()  {
        this.add(this.canvasPanel = new CanvasPanel());
    }

    public static void main(final String[] array) {
        Lab0504 lab0504 = new Lab0504();
        lab0504.setTitle("Chessboard");
        lab0504.setSize(400, 400);
        lab0504.setLocationRelativeTo(null);
        lab0504.setDefaultCloseOperation(3);
        lab0504.setVisible(true);
    }

    class CanvasPanel extends JPanel
    {
        @Override
        protected void paintComponent(final Graphics g) {
            super.paintComponent(g);
            final int n = this.getWidth() / 8;
            final int n2 = this.getHeight() / 8;
            for (int i = 0; i < 8; ++i) {
                for (int j = 0; j < 8; ++j) {
                    Color color;
                    if (i % 2 == 0) {
                        color = ((j % 2 == 0) ? Color.BLACK : Color.WHITE);
                    }
                    else {
                        color = ((j % 2 == 0) ? Color.WHITE : Color.BLACK);
                    }
                    g.setColor(color);
                    g.fillRect(n * j, n2 * i, n, n2);
                }
            }
        }
    }
}
