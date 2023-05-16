package Vista;

import Main.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicButtonUI;
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
        JButton calculateButton = new JButton("Calcula");
        calculateButton.setPreferredSize(new Dimension(150, 60));
        calculateButton.setUI(new BasicButtonUI()); // Establece la interfaz de usuario básica para el botón

        calculateButton.setBackground(new Color(255, 255, 255));
        calculateButton.setForeground(new Color(0, 0, 0));

        // Crear un borde redondeado
        Border roundedBorder = BorderFactory.createLineBorder(new Color(0, 0, 0));
        Border emptyBorder = BorderFactory.createEmptyBorder(5, 15, 5, 15);
        Border compoundBorder = BorderFactory.createCompoundBorder(roundedBorder, emptyBorder);
        calculateButton.setBorder(compoundBorder);

        // Personalizar la fuente del texto
        calculateButton.setFont(new Font("Arial", Font.BOLD, 12));

        // Quitar el efecto de enfoque
        calculateButton.setFocusPainted(false);
        c.gridx = 0;
        c.gridy = 1; // Adjust the gridy value to move it higher
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.NORTH;
        c.insets = new Insets(30, 50, 10, 50);
        add(calculateButton, c);
        calculateButton.addActionListener(this);

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

            case "Calcula":
                prog.notificar("Calcula");
                System.out.println("Calcula");
                break;
        }
    }
    public String getSelected1(){
        return (String) comboBox1.getSelectedItem();
    }

    public String getSelected2(){
        return (String) comboBox2.getSelectedItem();
    }

    public void setResultLabel(String txt){
        resultLabel.setText(txt);
    }


}
