package Vista;

import Main.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Vista extends JFrame implements ActionListener, PerEsdeveniments {
    private final Main prog;
    private final Panell panell;
    JLabel resultLabel;
    JComboBox<String> comboBox1;
    JComboBox<String> comboBox2;
    JCheckBox checkBox;

    public Vista(String s, Main p){
        super(s);
        prog = p;
        panell = new Panell(400, 300, prog.getModel());

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

// Create first JComboBox and add to panel
        comboBox1 = new JComboBox<>(new String[]{"CAT", "DEN", "ENG","FRA","DEU","ITA","NOR","POR","ESP","SWE"});
        comboBox1.setPreferredSize(new Dimension(200, 50));
        c.gridx = 0;
        c.gridy = 0; // Adjust the gridy value to move it higher
        c.insets = new Insets(30, 50, 10, 50);
        c.anchor = GridBagConstraints.NORTH;
        comboBox1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> combo = (JComboBox<String>) e.getSource();
                String selectedOption = (String) combo.getSelectedItem();
                combo.getSelectedItem();
                prog.getModel().loadLang(selectedOption);
            }
        });
        add(comboBox1, c);

// Create second JComboBox and add to panel
        comboBox2 = new JComboBox<>(new String[]{"Tots","CAT", "DEN", "ENG","FRA","DEU","ITA","NOR","POR","ESP","SWE"});
        comboBox2.setPreferredSize(new Dimension(200, 50));
        c.gridx = 1;
        c.gridy = 0; // Adjust the gridy value to move it higher
        c.insets = new Insets(30, 50, 10, 50);
        c.anchor = GridBagConstraints.NORTH;
        comboBox2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> combo = (JComboBox<String>) e.getSource();
                String selectedOption = (String) combo.getSelectedItem();
                prog.getModel().loadLang(selectedOption);
            }
        });
        add(comboBox2, c);

// Create "Calculate" button and add to panel
        JButton calculateButton = new JButton("Calculate");
        calculateButton.setPreferredSize(new Dimension(150, 60));
        c.gridx = 0;
        c.gridy = 1; // Adjust the gridy value to move it higher
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.NORTH;
        c.insets = new Insets(30, 50, 10, 50);
        add(calculateButton, c);

        // Create checkbox with two options
        checkBox = new JCheckBox("Optimitzat");
        checkBox.setPreferredSize(new Dimension(100, 30));
        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(30, 10, 10, 50);
        add(checkBox, c);



        resultLabel = new JLabel("Aqui se mostra el resultat");
        c.gridx = 0;
        c.gridy = 2; // Position it below the button
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(10, 50, 30, 50);
        add(resultLabel, c);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String comanda = e.toString();
        //System.out.println(comanda);
        int a = comanda.indexOf(",cmd=") + 5;
        comanda = comanda.substring(a, comanda.indexOf(",", a));
        System.out.println(comanda);
        try {
            prog.notificar(comanda);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        panell.repaint();
    }

    public void canviaEtiqueta(String lan1,String lan2,int dist){
        resultLabel.setText("Distància de "+lan1+" a "+lan2+" és: "+dist);
    }

    public void mostrar() {
        this.pack();
        this.setVisible(true);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Error: "+e.toString());;
        }
        this.revalidate();
        this.repaint();
    }


    @Override
    public void notificar(String s) throws InterruptedException {
        switch (s) {
            case "Repintar":

                break;
            case "fitxerLlegit":

                break;
        }
    }


}
