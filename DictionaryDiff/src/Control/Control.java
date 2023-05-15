package Control;

import Levenshtein.Levenstein;
import Main.Main;
import Main.PerEsdeveniments;
import Model.Idioma;

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
            String idioma1 = prog.getVista().getSelected1();
            if(prog.getVista().getSelected2().equals("Tots")){

            }else{
                String idioma2 = prog.getVista().getSelected2();
                this.prog.getModel().setIdioma1(new Idioma(idioma1));
                this.prog.getModel().setIdioma2(new Idioma(idioma2));
                Levenstein alg = new Levenstein(this.prog);
                System.out.println(alg.distEntre2Langs());
            }
            //
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