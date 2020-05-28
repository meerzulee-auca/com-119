import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Lab0505 extends JFrame {
    Model model;
    CanvasPanel canvasPanel;

    Lab0505() {
        this.model = new Model();
        (this.canvasPanel = new CanvasPanel()).setFocusable(true);
        this.add(this.canvasPanel);
        this.canvasPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == 39) {
                    Lab0505.this.model.moveRight();
                }
                else if (keyEvent.getKeyCode() == 37) {
                    Lab0505.this.model.moveLeft();
                }
                else if (keyEvent.getKeyCode() == 38) {
                    Lab0505.this.model.moveUp();
                }
                else if (keyEvent.getKeyCode() == 40) {
                    Lab0505.this.model.moveDown();
                }
                Lab0505.this.repaint();
            }
        });
    }

    public static void main(final String[] array) {
        final Lab0505 lab0505 = new Lab0505();
        lab0505.setTitle("Move robot");
        lab0505.setSize(400, 400);
        lab0505.setLocationRelativeTo(null);
        lab0505.setDefaultCloseOperation(3);
        lab0505.setVisible(true);
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
            g.setColor(Color.RED);
            g.fillOval(n * model.col, n2 * model.row, n, n2);
        }
    }
}
