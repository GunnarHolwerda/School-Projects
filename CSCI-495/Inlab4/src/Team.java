/**
 * Created by Gunnar on 2/9/2016.
 */
public class Team {

    private String teamName;
    private Player p1, p2, p3, p4;

    Team(String name, Player p1, Player p2, Player p3, Player p4) {
        this.teamName = name;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }

    public int getTotalRounds() {
        return p1.getRounds() + p2.getRounds() + p3.getRounds() + p4.getRounds();
    }

    public int getTotalScore() {
        return p1.getTotalScore() + p2.getTotalScore() + p3.getTotalScore() + p4.getTotalScore();
    }

    public float getTotalRoundAverage() {
        return (float)getTotalScore() / (float)getTotalRounds();
    }

    public void printStats() {
        System.out.println(teamName + ":");
        System.out.println("\tNumber of Rounds: " + getTotalRounds());
        System.out.println("\tTotal Score: " + getTotalScore());
        System.out.println("\tRound Average: " + getTotalRoundAverage());
        System.out.println("\tPlayers:");
        p1.printStats();
        p2.printStats();
        p3.printStats();
        p4.printStats();
    }
}
