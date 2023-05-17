package Control;

import Levenshtein.Levenstein;
import Main.Main;
import Main.PerEsdeveniments;
import Model.Idioma;
import Model.Model;

import java.io.FileNotFoundException;

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
                double[] distancias = new double[prog.getModel().NDicts()];
                for (int i = 0; i < distancias.length; i++) {
                    try {
                        prog.getModel().setIdioma2(new Idioma(prog.getModel().getDict(i)));
                        Levenstein alg = new Levenstein(this.prog);
                        distancias[i]= alg.distEntre2Langs();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
                prog.getVista().setResultLabel("La distància amb tots els idiomes es "+distancias);
            }else{
                String idioma2 = prog.getVista().getSelected2();
                try {
                    this.prog.getModel().setIdioma1(new Idioma(idioma1));
                    this.prog.getModel().setIdioma2(new Idioma(idioma2));

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                Levenstein alg = new Levenstein(this.prog);
                //System.out.println(alg.distEntre2Langs());
                if(prog.getModel().isOptimitzat()){
                    prog.getVista().setResultLabel("La distància entre els dos idiomes (OPTIMITZAT): "+alg.distOptim());
                }else{
                    prog.getVista().setResultLabel("La distància entre els dos idiomes: "+alg.distEntre2Langs());
                }

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