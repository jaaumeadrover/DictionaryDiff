package Vista;

import javax.swing.*;
import Model.Model;


public class Panell extends JPanel {
    private final Model mod;

    public Panell(Model mod) {
        this.mod = mod;
        this.setPreferredSize(new Dimension(w, h));
    }
}
