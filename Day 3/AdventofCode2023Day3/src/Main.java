import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static ArrayList<String> csvReader() {

        ArrayList<String> output = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\tdiggs\\OneDrive - eogresources.com\\Desktop\\Advent of Code\\Day 3\\sample.txt"))) {

            String item;

            while ((item = br.readLine()) != null) {

                output.add(item);

            }

        } catch (IOException e) {

            System.err.println("Error reading CSV file: " + e.getMessage());

        }

        return output;

    }

    public static void main(String[] args) {



    }
}