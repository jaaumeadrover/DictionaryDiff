package Levenshtein;

import Main.Main;
import Model.Idioma;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Levenstein {
    public Main prog;

    public Levenstein(Main p){
        prog=p;
    }
    public int min(int x, int y, int z)
    {
        if (x <= y && x <= z)
            return x;
        if (y <= x && y <= z)
            return y;
        else
            return z;
    }

    public int editDist(String str1, String str2, int m, int n) {

        if (m == 0)
            return n;

        if (n == 0)
            return m;


        if (str1.charAt(m - 1) == str2.charAt(n - 1)) {
            return editDist(str1, str2, m - 1, n - 1);
        }

        return 1 + this.min(
                editDist(str1, str2, m, n - 1), // Insert
                editDist(str1, str2, m - 1, n), // Remove
                editDist(str1, str2, m - 1, n - 1) // Replace
                );
    }

    //ALGORITME NO OPTIMITZAT
    public double distEntre2Langs() {
        //Referenciam els llenguatges seleccionats
        String[] llenguatge1=(prog.getModel().getIdioma1().getWords());
        String[] llenguatge2=prog.getModel().getIdioma2().getWords();

        String str1="";
        String str2="";

        int minDist;
        Double mean1 = 0.0;

        for (int i = 0; i <llenguatge1.length; i++) {
            str1=llenguatge1[i];
             minDist= Integer.MAX_VALUE;
           for (int j = 0; j < llenguatge2.length; j++) {
                str2=llenguatge2[j];
                //Levenstein
               int dist=this.editDist(str1,str2,str1.length(),str2.length());
               if(dist<minDist){
                    minDist=dist;
                    System.out.println();
               }
            }
            System.out.println("MIN: "+minDist);
           mean1 += minDist;
        }
        mean1 = mean1 / llenguatge1.length;

      Double mean2 = 0.0;

        for (int i = 0; i <llenguatge2.length; i++) {
            str1=llenguatge2[i];
            minDist= Integer.MAX_VALUE;
            for (int j = 0; j < llenguatge1.length; j++) {
                str2=llenguatge1[j];
                //Levenstein
                int dist=this.editDist(str1,str2,str1.length(),str2.length());
                if(dist<minDist){
                    minDist=dist;
                }
            }
            mean2 += minDist;
        }
        mean2 = mean2 / llenguatge1.length;

        //min
        return Math.sqrt((mean1*mean1)+(mean2*mean2));
    }
    public double[] distTots() throws FileNotFoundException {
        //Referenciam els llenguatges seleccionats
        String[] llenguatge1 = (prog.getModel().getIdioma1().getWords());
        double[] distancias = new double[prog.getModel().NDicts()];

        String str1 = "";
        String[] llenguatge2;
        String str2 = "";
        for (int h = 0; h < prog.getModel().NDicts(); h++) {

            llenguatge2 = new Idioma(prog.getModel().getDict(h)).getWords();

            int minDist = Integer.MAX_VALUE;
            Double mean1 = 0.0;

            str1=llenguatge1[0];

            mean1 = mean1 / llenguatge1.length;

            Double mean2 = 0.0;

            for (int i = 0; i < llenguatge2.length; i++) {
                str1 = llenguatge2[i];
                for (int j = 0; j < llenguatge1.length; j++) {
                    str2 = llenguatge1[j];
                    //Levenstein
                    int dist = this.editDist(str1, str2, str1.length(), str2.length());
                    if (dist < minDist) {
                        minDist = dist;
                    }
                }
                mean2 += minDist;
            }
            mean2 = mean2 / llenguatge1.length;

            //min
            distancias[h]= Math.sqrt((mean1 * mean1) + (mean2 * mean2));
        }
        return distancias;
    }
    public double distOptim(){
        //Referenciam els llenguatges seleccionats
        String[] llenguatge1=(prog.getModel().getIdioma1().getWords());
        String[] llenguatge2=prog.getModel().getIdioma2().getWords();

        String str1="";
        String str2="";

        int minDist;
        Double mean1 = 0.0;

        for (int i = 0; i <llenguatge1.length; i++) {
            str1=llenguatge1[i];
            minDist= Integer.MAX_VALUE;
            for (int j = 0; j < llenguatge2.length; j++) {
                str2=llenguatge2[j];
                if(str1.length()!=str2.length()){
                    break;
                }
                //Levenstein
                int dist=this.editDist(str1,str2,str1.length(),str2.length());
                if(dist<minDist){
                    minDist=dist;
                    System.out.println();
                }
            }
            System.out.println("MIN: "+minDist);
            mean1 += minDist;
        }
        mean1 = mean1 / llenguatge1.length;

        Double mean2 = 0.0;

        for (int i = 0; i <llenguatge2.length; i++) {
            str1=llenguatge2[i];
            minDist= Integer.MAX_VALUE;
            for (int j = 0; j < llenguatge1.length; j++) {
                str2=llenguatge1[j];
                if(str1.length()!=str2.length()){
                    break;
                }
                //Levenstein
                int dist=this.editDist(str1,str2,str1.length(),str2.length());
                if(dist<minDist){
                    minDist=dist;
                }
            }
            mean2 += minDist;
        }
        mean2 = mean2 / llenguatge1.length;

        //min
        return Math.sqrt((mean1*mean1)+(mean2*mean2));

    }


}
