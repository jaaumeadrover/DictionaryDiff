package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Idioma {
    private List<String> words;

    private List<String> sortedWords;

    private int indexos[];


    String nom;

    public Idioma( String nom) throws FileNotFoundException {
        String currentDir = Paths.get("").toAbsolutePath().toString();
        this.nom = nom;

        //Llegim paraules originals
        String filename=currentDir +"/data/Raw/"+nom+".txt";
        initWords(filename);

        //Obtenim paraules ordenades
        filename=currentDir+"/data/Sorted/"+nom+"_sorted.txt";
        initSortedWords(filename);

        //Obtenim meta-informació
        filename=currentDir+"/data/Metadata/"+nom+"_metadata.txt";
        initMetadata(filename);
        System.out.println();
    }

    private void initWords(String filename){
        words = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void initSortedWords(String filename){
        sortedWords = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                sortedWords.add(line);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void initMetadata(String filename){
        indexos=new int[15];
        String numbers[];

        //Inicialitzam array nombres
        for (int i = 0; i < indexos.length; i++) {
            indexos[i]=-1;
        }
        indexos[1]=0; //les paraules amb len 1 comencen desde 0


        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                numbers=line.split(",");
                int longitud=Integer.parseInt(numbers[0]);
                int index=Integer.parseInt(numbers[1]);
                indexos[longitud]=index;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public String[] getWords(){
        return words.toArray(new String[0]);
    }

    public String[] getSortedWords(){
        return sortedWords.toArray(new String[0]);
    }

    public int[] getIndexos(){return this.indexos;}
}
