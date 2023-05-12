package Levenshtein;

import Main.Main;

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
        String[] llenguatge1=prog.getModel().getDict(1);
        String[] llenguatge2=prog.getModel().getDict(2);

        String str1="";
        String str2="";

        int minDist= Integer.MAX_VALUE;
        Double mean1 = 0.0;

        for (int i = 0; i <llenguatge1.length; i++) {
            str1=llenguatge1[i];
           for (int j = 0; j < llenguatge2.length; j++) {
                str2=llenguatge2[j];
                //Levenstein
               int dist=this.editDist(str1,str2,str1.length(),str2.length());
               if(dist<minDist){
                    minDist=dist;
               }
            }
           mean1 += minDist;
        }
        mean1 = mean1 / llenguatge1.length;

      Double mean2 = 0.0;

        for (int i = 0; i <llenguatge2.length; i++) {
            str1=llenguatge2[i];
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


}
