import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static String replaceWordsWithNumbers(String input) {
        HashMap<String, String> replaceMap = new HashMap<>();
        replaceMap.put("zero", "z0o");
        replaceMap.put("one", "o1e");
        replaceMap.put("two", "t2o");
        replaceMap.put("three", "t3e");
        replaceMap.put("four", "f4r");
        replaceMap.put("five", "f5e");
        replaceMap.put("six", "s6x");
        replaceMap.put("seven", "s7n");
        replaceMap.put("eight", "e8t");
        replaceMap.put("nine", "n9e");

        String result = input;

        for (String key : replaceMap.keySet()) {
            result = result.replaceAll(key, replaceMap.get(key));
        }

        return result;
    }

    public static int parseAndSum(ArrayList<String> items) {

        int sum = 0;

        for (String item : items) {
            String noChar = replaceWordsWithNumbers(item).replaceAll("[^0-9]", "");
            char first = noChar.charAt(0);
            char last = noChar.charAt(noChar.length() -1 );
            int result = Integer.parseInt(String.valueOf(first) + last);
            sum += result;
        }

        return sum;

    }

    public static void main(String[] args) {

        ArrayList<String> items = new ArrayList<>();

        // CSV Reader
        try (BufferedReader br = new BufferedReader(new FileReader("./Day 1/input.txt"))) {

            String item;
            while ((item = br.readLine()) != null) {
                items.add(item);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        // CSV Reader

        int result = parseAndSum(items);

        System.out.println(result);

    }

}
