/**
 * The driver class for the Bowling Program
 * 
 * @author Gunnar Holwerda
 * @version October 11, 2012
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
        
        percentStrikes = 0.0;
        percentSpares = 0.0;
        percentOpenFrames = 1.0;        // a game that will not score very high
        framesToBowl = 10;
        
        bowler = new Bowler(percentStrikes, percentSpares, percentOpenFrames, framesToBowl);
        
        bowler.bowlOneGame();
        
        bowler.setFramesToBowl(10);              // play a 5 frame game instead of a 10 frame one
        
        bowler.bowlOneGame();
        
        bowler.setSparePercentage(1.0);         // a game that will score OK 1.0
        bowler.setOpenFramePercentage(0.0);
        
        bowler.bowlOneGame();
        
        bowler.setSparePercentage(0.0);         //0
        bowler.setStrikePercentage(1.0);        // a perfect game!1
        
        bowler.bowlOneGame();
    }       
}