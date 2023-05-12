package Main;

import java.awt.*;
import javax.swing.*;

public class MyPanel extends JPanel {
    private JComboBox<String> comboBox1;
    private JComboBox<String> comboBox2;
    private JButton calculateButton;

    public MyPanel() {
        setPreferredSize(new Dimension(800, 800));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Create first JComboBox and add to panel
        comboBox1 = new JComboBox<>(new String[]{"Option 1", "Option 2", "Option 3"});
        comboBox1.setPreferredSize(new Dimension(200, 50));
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(100, 50, 50, 50);
        add(comboBox1, c);

        // Create second JComboBox and add to panel
        comboBox2 = new JComboBox<>(new String[]{"Option A", "Option B", "Option C"});
        comboBox2.setPreferredSize(new Dimension(200, 50));
        c.gridx = 1;
        add(comboBox2, c);

        // Create "Calculate" button and add to panel
        calculateButton = new JButton("Calculate");
        calculateButton.setPreferredSize(new Dimension(300, 100));
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(50, 50, 100, 50);
        add(calculateButton, c);

        JLabel resultLabel = new JLabel("Text to display");
        c.gridx = 0;
        c.gridy = 2; // Position it below the button
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(10, 50, 30, 50);
        add(resultLabel, c);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("JPanel Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MyPanel panel = new MyPanel();
        frame.getContentPane().add(panel);

        frame.pack();
        frame.setVisible(true);
    }
}

