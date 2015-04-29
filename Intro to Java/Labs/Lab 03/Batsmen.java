
/**
 * Write a description of class Batsmen here.
 * 
 * @author Gunnar Holwerda
 * @version 9/16/12
 */
public class Batsmen
{
    private String name;    //name of the player
    private int innings,    //number of innings they played
                notOut,     //the number of innings that the batsmen was not out
                runs,       //the number of runs the player has
                highScore,  //the highscore of the player
                ballsFaced; //the number of balls faced by the player
    
    //constructor for the class Batsmen
    Batsmen(String inName, int inInnings, int inNotOut, int inRuns, int inHighScore, int inBallsFaced)
    {
        name = inName;
        innings = inInnings;
        notOut = inNotOut;
        runs = inRuns;
        highScore = inHighScore;
        ballsFaced = inBallsFaced;
    }
    
    //returns the player's batting average
    public double average()
    {
        return (double) runs / (innings - notOut);
    }
    
    //returns the player's scoring rate
    public double scoringRate()
    {
        return ((double)runs / ballsFaced) * 100;
    }
}
