import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
Code to show a list of files.
Helps to resolve relative file path issues.
    File file = new File(".");
    for(String fileNames : file.list()) System.out.println(fileNames);
*/

public class Main {

    public static List<String> reader(String filePath) {
        BufferedReader reader = null;
        List<String> lines = new ArrayList<>();
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

    public static void main(String[] args) {

        String filePath = "./Day 3/AdventofCode2023Day3/sample.txt";

        List<String> lines = reader(filePath);

        for (String line : lines) {
            System.out.println(line);
        }

    }
}