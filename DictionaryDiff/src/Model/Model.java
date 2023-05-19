package Model;

import Main.Main;

public class Model {
    private final Main prog;

    private final String[] dicts = {"CAT", "DEN", "ENG","FRA","DEU","ITA","NOR","POR","ESP","SWE"};

    private Idioma idioma1;

    private Idioma idioma2;


    public boolean tots;

    private int dist;
    private boolean optimitzat;



    public Model(Main prog) {
        this.prog = prog;
        optimitzat=false;
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
    public void setTots(boolean t){
        this.tots=t;
    }

    public void setIdioma1(Idioma id){
        this.idioma1 = id;
    }

    public void setIdioma2(Idioma id){
        this.idioma2 = id;
    }

    public void setOptimitzat(boolean bol){
        this.optimitzat=bol;
    }

    public Idioma getIdioma1() {
        return idioma1;
    }
    public Idioma getIdioma2() {
        return idioma2;
    }

    public void printea (){
        System.out.println(idioma1.nom);
    }
    public String getDict (int i){
        return dicts[i];
    }

    public boolean isOptimitzat(){
        return this.optimitzat;
    }

    public int NDicts(){
        return dicts.length;
    }

    public String[] getIdiomes(){
        return this.dicts;
    }
}

