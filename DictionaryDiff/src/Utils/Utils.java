package Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static String[] readLinesToArray(String filename) throws IOException {
        List<String> lines = new ArrayList<String>();
        String currentDir = Paths.get("").toAbsolutePath().toString();
        BufferedReader reader = new BufferedReader(new FileReader(currentDir +"/data/"+filename));
        String line = reader.readLine();
        while (line != null) {
            lines.add(line);
            line = reader.readLine();
        }
        reader.close();
        return lines.toArray(new String[0]);
    }
}
