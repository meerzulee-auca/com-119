import javax.swing.*;
import java.awt.*;

public class Lab0501 extends JFrame {
    JPanel mainPanel;
    JPanel controlPanel;
    JButton redButton;
    JButton greenButton;
    JButton blueButton;

    public Lab0501() {
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
    }

    public static void main(String[] args) {
        Lab0501 lab0501 = new Lab0501();
        lab0501.setTitle("First GUI App");
        lab0501.setSize(400, 400);
        lab0501.setLocationRelativeTo(null);
        lab0501.setDefaultCloseOperation(3);
        lab0501.setVisible(true);
    }
}
