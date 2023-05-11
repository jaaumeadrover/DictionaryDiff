package Main;

import Model.Model;
import Control.Control;
import Vista.Vista;


public class Main implements PerEsdeveniments{
    private Model mod;    // Punter al Model
    private Vista vis;    // Punter a la Vista
    private Control con;  // punter al Control

    /*
           Construcció de l'esquema MVC
        */
    private void inicio() throws IOException {
        mod = new Model(this); //incluye lectura de fichero-grafo
        con = null;
        vis = new Vista("Complexitat Algorítmica I", this);
        vis.mostrar();
    }

    public static void main(String[] args) throws IOException {
        //Mesurament.mesura();
        (new Main()).inicio();
    }
    /*
           Funció per poder comunicar al programa principal que l'ha clicat un botó
           a la vista.
        */
    @Override
    public void notificar(String s) throws InterruptedException {
        switch(s){
            case "Llegir fitxer":

                vis.notificar("fitxerLlegit");
                //mod.setLlegit(false);
                break;
            case "Borra":

                vis.notificar("Repintar");
                break;
            case "Calcula":
                this.con = new Control(this);
                con.notificar("Calcula");
                break;
            default  : { //cas per poder netejar la pantalla
                this.vis.mostrar();
                break;
            }
        }
    }
    //GETTERS
    //Mètode public de retorn de la instància del model de dades
    public Model getModel() {
        return this.mod;
    }
    //Mètode public que ens retorna la vista
    public Vista getVista(){
        return this.vis;
    }
}