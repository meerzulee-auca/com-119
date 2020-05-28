import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Lab0502 extends JFrame {
    JPanel mainPanel;
    JPanel controlPanel;
    JButton redButton;
    JButton greenButton;
    JButton blueButton;

    class ButtonListener implements ActionListener
    {
        Color color;

        ButtonListener(Color color) {
            this.color = color;
        }

        @Override
        public void actionPerformed(final ActionEvent actionEvent) {
            Lab0502.this.mainPanel.setBackground(this.color);
        }
    }

    Lab0502() {
        this.mainPanel = new JPanel();
        this.controlPanel = new JPanel();
        this.redButton = new JButton("RED");
        this.greenButton = new JButton("GREEN");
        this.blueButton = new JButton("BLUE");
        this.setLayout(new BorderLayout());
        this.mainPanel.setBackground(Color.RED);
        this.add(this.mainPanel, "Center");
        this.controlPanel.setBackground(Color.DARK_GRAY);
        this.controlPanel.add(this.redButton);
        this.controlPanel.add(this.greenButton);
        this.controlPanel.add(this.blueButton);
        this.add(this.controlPanel, "South");
        this.redButton.addActionListener(new ButtonListener(Color.RED));
        this.greenButton.addActionListener(new ButtonListener(Color.GREEN));
        this.blueButton.addActionListener(new ButtonListener(Color.BLUE));
    }

    public static void main(final String[] array) {
        final Lab0502 lab0502 = new Lab0502();
        lab0502.setTitle("First GUI App");
        lab0502.setSize(400, 400);
        lab0502.setLocationRelativeTo(null);
        lab0502.setDefaultCloseOperation(3);
        lab0502.setVisible(true);
    }

}
