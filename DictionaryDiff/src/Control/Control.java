package Control;

import Djikstra.Dijkstra;
import Main.Main;
import Main.PerEsdeveniments;

/**
 * AUTOR: ATA2
 * CLASSE: Control
 * DATA CREACIÓ: 23/04/2023
 * FUNCIONALITAT: implementa el controlador que avisa al joc quan ha de trobar
 * una solució al problema proposat per l'usuari.
 */
public class Control extends Thread implements PerEsdeveniments {
    private final Main prog;
    private String msg;



    public Control(Main p){
        this.prog=p;

    }

    @Override
    public void run() {
        if(msg.startsWith("Calcula")){
            //inicialitzar dades Model

            try {
                prog.getVista().notificar("Repintar");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    public void notificar(String s) {
        msg=s;
        this.start();
    }
}