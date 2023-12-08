import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        String filePath = "./Day 4/input.txt";

        ArrayList<String> lines = CSVUtility.reader(filePath);
        ArrayList<Card> cards = new ArrayList<>();

        int totalScore = 0;

        for (String line : lines) {
            int id = Integer.parseInt(line.split(":")[0].replaceAll("[^0-9]", ""));
            String winsAndDraws = line.split(":")[1].trim();
            String wins = winsAndDraws.split("\\|")[0].trim();
            String draws = winsAndDraws.split("\\|")[1].trim();
            int[] winsIntArray = Arrays.stream(wins.split("\\s+")).mapToInt(Integer::parseInt).toArray();
            int[] drawsIntArray = Arrays.stream(draws.split("\\s+")).mapToInt(Integer::parseInt).toArray();
            cards.add(new Card(id, winsIntArray, drawsIntArray));
        }
        for (Card card : cards) {
            totalScore += card.calcScore();
        }
        System.out.println(totalScore);

    }

}