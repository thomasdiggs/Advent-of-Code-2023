import java.util.Arrays;

public class Card {

    public int id;
    public int[] wins;
    public int[] draws;
    public int score;

    public Card(int id, int[] wins, int[] draws) {
        this.id = id;
        this.wins = wins;
        this.draws = draws;
    }

    public int calcScore() {
        int count = 0;
        for (int draw : this.draws) {
            for (int win : this.wins) {
                if (draw == win) {
                    count++;
                }
            }
        }
        this.score = 0;
        for (int i = 1; i <= count; i++) {
            if (i == 1) {
                this.score = 1;
            } else {
                this.score *= 2;
            }
        }
        return this.score;
    }

    @Override
    public String toString() {
        return "{Game ID: " + this.id +
                ", Wins: " + Arrays.toString(this.wins) +
                ", Draws: " + Arrays.toString(this.draws) +
                ", Score: " + this.score + "}";
    }
}
