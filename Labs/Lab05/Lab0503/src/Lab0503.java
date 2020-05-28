import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Lab0503 extends JFrame{
    JPanel panel;

    Lab0503() {
        (this.panel = new JPanel()).setBackground(Color.DARK_GRAY);
        this.add(this.panel);
        this.panel.addMouseMotionListener(new ButtonMouseMotionListener());
    }

    public static void main(final String[] array) {
        final Lab0503 lab0503 = new Lab0503();
        lab0503.setTitle("Move mouse");
        lab0503.setSize(400, 400);
        lab0503.setLocationRelativeTo(null);
        lab0503.setDefaultCloseOperation(3);
        lab0503.setVisible(true);
    }

    class ButtonMouseMotionListener implements MouseMotionListener
    {
        @Override
        public void mouseDragged(final MouseEvent mouseEvent) {
        }

        @Override
        public void mouseMoved(final MouseEvent mouseEvent) {
            Lab0503.this.setTitle(mouseEvent.getX() + ", " + mouseEvent.getY());
        }
    }
}
