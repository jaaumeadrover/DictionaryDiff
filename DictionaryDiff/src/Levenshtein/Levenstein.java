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
        mean2 = mean2 / llenguatge2.length;

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
        String[] llenguatge1=(prog.getModel().getIdioma1().getSortedWords());
        String[] llenguatge2=prog.getModel().getIdioma2().getSortedWords();

        String str1="";
        String str2="";

        int minDist;
        Double mean1 = 0.0;

        //LLENGUATGE 1 AMB LLENGUATGE 2
        for (int i = 0; i <llenguatge1.length; i++) {
            str1=llenguatge1[i];
            minDist= Integer.MAX_VALUE;

            int [] indexos = trobarIndex(str1.length(),prog.getModel().getIdioma2().getIndexos());//index de longitud menor,igual

            minDist=treuMin(str1,llenguatge2,indexos); //treu la mínima distància entre un interval de paraules donat

            if(minDist>1){//miram al voltant
                minDist=treuMinVoltant(str1,minDist,indexos,2);
            }

            mean1 += minDist;
        }
        mean1 = mean1 / llenguatge1.length;




        Double mean2 = 0.0;

        //LLENGUATGE 2 AMB LLENGUATGE1
        for (int i = 0; i <llenguatge2.length; i++) {
            str1=llenguatge2[i];
            minDist= Integer.MAX_VALUE;

            int[] indexos=trobarIndex(str1.length(),prog.getModel().getIdioma1().getIndexos()); //trobam indexos




            minDist=treuMin(str1,llenguatge1,indexos); //treu la mínima distància entre un interval de paraules donat


            if(minDist>1){//miram al voltant
                minDist=treuMinVoltant(str1,minDist,indexos,1);
            }


            mean2 += minDist;
        }
        mean2 = mean2 / llenguatge2.length;

        //min
        return Math.sqrt((mean1*mean1)+(mean2*mean2));

    }

    //Mètode per a trobar l'índex de paraules d'una determinada longitud o les més properes
    //Retorna l'índex del primer i del darrer i les seves respectives posicions
    private int[] trobarIndex(int longitud,int[] indexos){
        int []sol=new int[4];
        int idxDret=0;
        int posDreta=0;
        int idxEsq=0;
        int posEsq=0;
        int longitud2=longitud;
        if(longitud>indexos.length-1){
            longitud2=indexos.length-1;
        }

        for (int i = longitud2; i < indexos.length; i++) {//recorregut dreta
            if(indexos[i]!=-1){
                idxDret=indexos[i];
                posDreta=i;
                break;
            }
        }

        for (int i = longitud2; i > 0; i--) {//recorregut esquerra
            if(indexos[i]!=-1){
                idxEsq=indexos[i];
                posEsq=i;
                break;
            }
        }


        //Si existeixen paraules de la mateixa longitud
        if(posDreta==longitud){
            sol[0]=idxDret;
            sol[1]=indexos[posDreta+1];
            sol[2]=posDreta;
            sol[3]=posDreta+1;
            return sol;
        }else{//si no, cercam l'índex del qui s'atraqui més

            if((longitud2-posEsq)<(posDreta-longitud2)){
                sol[0]=idxEsq;

                if(posEsq-1<0){
                    sol[1]=0;
                    sol[3]=1;
                }else{
                    sol[1]=indexos[posEsq-1];
                    sol[3]=posEsq-1;
                }
                sol[2]=posEsq;
                return sol;
            }else{
                sol[0]=idxDret;

                if(posDreta+1>(indexos.length-1)){
                    sol[1]=indexos[indexos.length-1];
                    sol[3]=indexos.length-1;
                }else{
                    sol[1]=indexos[posDreta+1];
                    sol[3]= posDreta+1;
                }
                sol[2]=posDreta;
                return sol;
            }
        }

    }
    public int treuMin(String paraula,String[] llenguatge, int[] indexos){
        int minDist = Integer.MAX_VALUE;
        String str2 = "";

        if(indexos[0]>indexos[1]){//recorregut cap a la dreta

            for (int j = indexos[0]; j > indexos[1]; j--) {
                str2=llenguatge[j];
                int dist=this.editDist(paraula,str2,paraula.length(),str2.length());
                if(dist<minDist){//Si la distància és més petita, update
                    minDist=dist;
                }
            }

        }else{//recorregut cap a l'esquerra

            for (int j = indexos[0]; j < indexos[1] ; j++) {
                str2=llenguatge[j];
                int dist=this.editDist(paraula,str2,paraula.length(),paraula.length());
                if(dist<minDist){//Si la distància és més petita, update
                    minDist=dist;
                }
            }

        }
        return minDist;
    }

    public int treuMinVoltant(String paraula,int minDist,int[] indexos, int lang ){
        int minActual=minDist;
        Idioma llenguatge;

        if(lang==1){
                llenguatge=prog.getModel().getIdioma1();
        }else{
            llenguatge=prog.getModel().getIdioma2();
        }

        if(indexos[0]>indexos[1]) {//cap a l'esquerra
            int index;

            if((indexos[3]-(minDist+1))<0){ //si surt de l'array, la mínima posició és 0.
                index=llenguatge.getIndexos()[0];
            }else{
                index=llenguatge.getIndexos()[indexos[3]-(minDist+1)];
            }

            //recorregut esquerra
            for (int i = indexos[1]; i > index; i--) {
                String str2=llenguatge.getSortedWords()[i];
                int dist=this.editDist(paraula,str2,paraula.length(),str2.length());
                if(dist<minActual){//Si la distància és més petita, update
                    minActual=dist;
                }
            }

            if(indexos[2]+(minDist-1)>(llenguatge.getIndexos().length-1)){//si ens passam de l'array
                index=llenguatge.getIndexos()[llenguatge.getIndexos().length-1];
            }else{
                index=llenguatge.getIndexos()[indexos[2]+(minDist-1)];
            }

            //recorregut dreta
            for (int i = indexos[0]; i < index; i++) {
                String str2=llenguatge.getSortedWords()[i];
                int dist=this.editDist(paraula,str2,paraula.length(),str2.length());
                if(dist<minActual){//Si la distància és més petita, update
                    minActual=dist;
                }
            }

        }else{



            int index;


            if(indexos[2]-(minDist+1)<0){//si ens passam de l'array
                index=llenguatge.getIndexos()[0];
            }else{
                index=llenguatge.getIndexos()[indexos[2]-(minDist+1)];
            }

            //recorregut esquerra
            for (int i = indexos[0]; i > index; i--) {
                String str2=llenguatge.getSortedWords()[i];
                int dist=this.editDist(paraula,str2,paraula.length(),str2.length());
                if(dist<minActual){//Si la distància és més petita, update
                    minActual=dist;
                }
            }

            if(indexos[3]+(minDist-1)>llenguatge.getIndexos().length-1){
                index=llenguatge.getIndexos()[llenguatge.getIndexos().length-1];
            }else{
                index=llenguatge.getIndexos()[indexos[3]+(minDist-1)];
            }

            //recorregut dreta
            for (int i = indexos[1]; i < index; i++) {
                String str2=llenguatge.getSortedWords()[i];
                int dist=this.editDist(paraula,str2,paraula.length(),str2.length());
                if(dist<minActual){//Si la distància és més petita, update
                    minActual=dist;
                }
            }

        }

        return minActual;
    }


}
