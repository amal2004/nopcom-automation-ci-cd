package nopcom.autm.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {
    public static List<String[]> readCsv(String resourcePath) {
        List<String[]> rows = new ArrayList<>();
        try (InputStream is = CSVUtils.class.getClassLoader().getResourceAsStream(resourcePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if (first) { first = false; continue; }
                String[] cols = line.split(",");
                rows.add(cols);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to read csv: " + resourcePath, e);
        }
        return rows;
    }
}
