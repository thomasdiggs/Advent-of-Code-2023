import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static ArrayList<String> csvReader() {

        ArrayList<String> output = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\tdiggs\\OneDrive - eogresources.com\\Desktop\\Advent of Code\\Day 2\\input.txt"))) {

            String item;

            while ((item = br.readLine()) != null) {

                output.add(item);

            }

        } catch (IOException e) {

            System.err.println("Error reading CSV file: " + e.getMessage());

        }

        return output;

    }

    public static int sum(ArrayList<String> items) {

        int sum = 0;

        for (String item : items) {

            Pattern redPattern = Pattern.compile("(\\d+) red");
            Matcher redMatcher = redPattern.matcher(item);
            Pattern greenPattern = Pattern.compile("(\\d+) green");
            Matcher greenMatcher = greenPattern.matcher(item);
            Pattern bluePattern = Pattern.compile("(\\d+) blue");
            Matcher blueMatcher = bluePattern.matcher(item);

            boolean isAnyRedExceeds12 = false;
            boolean isAnyGreenExceeds13 = false;
            boolean isAnyBlueExceeds14 = false;

            while (redMatcher.find()) {

                int value = Integer.parseInt(redMatcher.group(1));

                if (value > 12) {

                    isAnyRedExceeds12 = true;
                    break;

                }

            }

            while (greenMatcher.find()) {

                int value = Integer.parseInt(greenMatcher.group(1));

                if (value > 13) {

                    isAnyGreenExceeds13 = true;
                    break;

                }

            }

            while (blueMatcher.find()) {

                int value = Integer.parseInt(blueMatcher.group(1));

                if (value > 14) {

                    isAnyBlueExceeds14 = true;
                    break;

                }

            }

            if (!(isAnyRedExceeds12 || isAnyGreenExceeds13 || isAnyBlueExceeds14)) {

                int gameID = Integer.parseInt((item.split(":")[0]).replaceAll("\\D", ""));
                sum += gameID;

            }

        }

        return sum;

    }

    public static int power(ArrayList<String> items) {

        int power = 0;

        for (String item : items) {

            Map<String, Integer> colorCounts = new HashMap<>();

            Pattern pattern = Pattern.compile("(\\d+)\\s(\\w+)");
            Matcher matcher = pattern.matcher(item);

            while (matcher.find()) {

                int count = Integer.parseInt(matcher.group(1));
                String color = matcher.group(2);

                colorCounts.put(color, colorCounts.getOrDefault(color, 0) < count ? count : colorCounts.getOrDefault(color, 0));

            }

            int temp = 1;

            for (Map.Entry<String, Integer> entry : colorCounts.entrySet()) {

                temp *= entry.getValue();

            }

            power += temp;

        }

        return power;

    }

    public static void main(String [] args) {

        ArrayList<String> items = csvReader();

        int sum = sum(items);
        System.out.println(sum);

        int power = power(items);
        System.out.println(power);

    }

}