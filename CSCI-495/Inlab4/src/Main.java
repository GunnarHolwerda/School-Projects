/**
 * A Golf League application for Inlab4.
 */
public class Main
{
    public static void main(String[] args)
    {
        // Scores pulled from  the last 3 PGA tournaments:
        // http://www.worldgolfchampionships.com/hsbc-champions/leaderboard.html
        // http://www.pgatour.com/tournaments/hyundai-tournament-of-champions/leaderboard.html
        // http://www.pgatour.com/tournaments/at-t-pebble-beach-national-pro-am/leaderboard.html

        //create instance of Player class and set the number of rounds and score
        Player p1 = new Player("Jordan Spieth");
        p1.setRounds(4 + 4 + 4);
        p1.setScore(273 + 262 + 271);

        //create instance of Player class and set the number of rounds and score
        Player p2 = new Player("Dustin Johnson");
        p2.setRounds(4 + 4 + 4);
        p2.setScore(272 + 277 + 270);

        // Note the last 2 players didn't play in the world golf championship
        // so they only have 2 tournaments of data.

        //create instance of Player class and set the number of rounds and score
        Player p3 = new Player("Jason Day");
        p3.setRounds(4 + 4);
        p3.setScore(277 + 270);

        //create instance of Player class and set the number of rounds and score
        Player p4 = new Player("Brandt Snedeker");
        p4.setRounds(4 + 4);
        p4.setScore(271 + 265);

        //create instance of Team class and assign Players
        Team t1 = new Team("Dream Foursome", p1, p2, p3, p4);

        //print out statistics for the team
        t1.printStats();
    }
}
