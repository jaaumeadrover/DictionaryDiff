package Model;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class Idioma {
    List<String> words;

    String filename;

    //String filesorted;

    String nom;

    public Idioma( String nom) throws FileNotFoundException {
        String currentDir = Paths.get("").toAbsolutePath().toString();
        this.filename=currentDir +"/data/"+nom+".txt";
        this.nom = nom;
        words = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("LINE: "+line);
                words.add(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println();
    }

    public void ordena(){
        // Ordenar el ArrayList por longitud de cadenas
        Collections.sort(words, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        });
    }
    public String[] getWords(){
        return words.toArray(new String[0]);
    }
}
