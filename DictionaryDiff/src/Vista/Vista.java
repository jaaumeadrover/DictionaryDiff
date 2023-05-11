package Vista;

import Main.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Vista extends JFrame implements ActionListener, PerEsdeveniments,MouseListener {
    private final Main prog;
    private final Panell panell;

    public Vista(String s, Main p){
        super(s);

        prog = p;
        this.getContentPane().setLayout(new BorderLayout());

        JPanel buttons = new JPanel();

        JButton b2 = new JButton("Llegir fitxer");
        b2.addActionListener(this);
        buttons.add(b2);

        JButton b1 = new JButton("Borra");
        b1.addActionListener(this);
        buttons.add(b1);

        JButton b4 = new JButton("Calcula");
        b4.addActionListener(this);
        buttons.add(b4);

        this.add(BorderLayout.NORTH, buttons);
        panell = new Panell(800, 800, prog.getModel());
        this.add(BorderLayout.CENTER, panell);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.addMouseListener(this);
        this.pack();
        this.setLocationRelativeTo(null);
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

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
