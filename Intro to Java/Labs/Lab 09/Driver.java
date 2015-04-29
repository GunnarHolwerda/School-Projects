/**
 * The driver class for Lab 9. 
 * 
 * @author John Paxton
 * @version October 29, 2012
 */

public class Driver
{
    public static void main (String [] args)
    {
        Bowler bowler;                  // an instance of the Bowler class
        
        double percentStrikes;          // percent of the time the bowler gets a strike
        double percentSpares;           // percent of the time the bowler gets a spare
        double percentOpenFrames;       // percent of the time the bowler has an open frame
        int framesToBowl;               // number of frames in a bowling game
        
        System.out.println("CSCI 111, Program 4");
        System.out.println("-------------------");
        
        percentStrikes = 1.0;
        percentSpares = 0.0;
        percentOpenFrames = 0.0;        // a game that will not score very high
        framesToBowl = 10;
        
        bowler = new Bowler(percentStrikes, percentSpares, percentOpenFrames, framesToBowl);
        
        bowler.bowlOneGame();
        
        bowler.setStrikePercentage(0.5);
        bowler.setOpenFramePercentage(0.5);
        
        bowler.bowlOneGame();
        
        bowler.setOpenFramePercentage(0.0);
        bowler.setSparePercentage(0.5);
        
        bowler.bowlOneGame();
        
        bowler.setOpenFramePercentage(0.25);
        bowler.setSparePercentage(0.25);
        
        bowler.bowlOneGame();
    }       
}