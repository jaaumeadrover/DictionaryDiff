package Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    private static final DecimalFormat decimalFormat = new DecimalFormat("#.###");

    public static double round(double d){
        decimalFormat.setRoundingMode(RoundingMode.UP);
        String v=decimalFormat.format(d);

        return Double.parseDouble(v);
    }

}
