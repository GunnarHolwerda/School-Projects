/**
 * Created by Gunnar on 2/9/2016.
 */
public class Player {

    private String name;
    private int rounds;
    private int totalScore;

    Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRounds(int rounds){
        this.rounds = rounds;
    }

    public int getRounds() {
        return rounds;
    }

    public void setScore(int score) {
        this.totalScore = score;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public float getRoundAverage() {
        return (float)totalScore / (float)rounds;
    }

    public void printStats() {
        System.out.printf("\t\t%s ---> # Rounds: %d, Score: %d, Round Average: %f\n",name, rounds, totalScore, getRoundAverage());
    }
}
