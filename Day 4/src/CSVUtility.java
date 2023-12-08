import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVUtility {

    public static ArrayList<String> reader(String filePath) {
        BufferedReader reader = null;
        ArrayList<String> lines = new ArrayList<>();
        try {
            File file = new File(filePath);
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            try {
                assert reader != null;
                reader.close();
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        return lines;
    }

}
