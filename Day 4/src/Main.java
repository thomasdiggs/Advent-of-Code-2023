import java.util.*;

public class Main {

    public static int countCopies(ArrayList<Card> cards) {
        int totalCopies = 0;
        for (Card card : cards) {
            for (int i = 1; i <= card.copies; i++) {
                for (int j = 1; j <= card.count; j++) {
                    if (cards.contains(cards.get(cards.indexOf(card) + j))) {
                        cards.get(cards.indexOf(card) + j).copies += 1;
                    }
                }
            }
            totalCopies += card.copies;
        }
        return totalCopies;
    }

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
        System.out.println("Total score: " + totalScore);

        int totalCopies = countCopies(cards);

        System.out.println("Total copies: " + totalCopies);

    }

}