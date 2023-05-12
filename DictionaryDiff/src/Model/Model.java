package Model;

import Main.Main;

public class Model {
    private final Main prog;

    private String[] lan1;

    private String[] lan2;

    private int dist;
    private boolean optimitzat;



    public Model(Main prog) {
        this.prog = prog;
        optimitzat=false;
    }
    //Mètode per obtenir els
    public String[] getDict(int i){
        if(i==1){
            return lan1;
        }else{
            return lan2;
        }
    }
    public void calcula(){
        //load lan1
        //load lan2

        //Si no està optimitzat
        if(!optimitzat){
            //crida levenstein sense optimitzar
        }else{
            //crida levenstein amb distàncies
        }
    }

    public void loadLang(String str){
        String f=str;
        if(str!="Tots"){
            if(optimitzat){
                f+="_sorted";
            }

            f+=".txt";
        }else{

        }
    }
}
