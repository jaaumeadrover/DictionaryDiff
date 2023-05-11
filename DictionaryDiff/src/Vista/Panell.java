package Vista;


import Model.Model;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Panell extends JPanel {
    private final Model mod;

    public Panell(int w, int h, Model mod) {
        this.mod = mod;
        this.setPreferredSize(new Dimension(w, h));
    }
    @Override
    public void repaint() {

    }

    @Override
    public void paint(Graphics g) {


    }
}
