import java.util.Scanner;
import java.lang.Math;

/**
 * Write a description of class Bowler here.
 * 
 * @author Gunnar Holwerda
 * @version October 11, 2012
 */
public class Bowler
{
    private double percentStrikes,                      //percent of frames that are strikes
                   percentSpares,                       //percent of frames that are spares
                   percentOpenFrames,                   //percent of the frames that will not be a spare or strike
                   ballWeight;
                   
    private int framesToBowl,                           //number of frames you are going to play
                totalScore = 0;                         //score of the game

    private int ballOnes[] = new int[10],               //array that holds all of the first balls you threw
                ballTwos[] = new int[10],               //array that holds all of the second balls you threw
                ballThrees[] = new int[10];             //array that holds all of the third balls you threw
    
    /*
     * Constructor for class Bowler
     * 
     * @param   inPercentStrikes    percentage of strikes of the bowler
     * @param   inPercentSpares     percentage of spares of the bowler
     * @param   inPercentOpenFrames percentage of frames that are open
     * @param   inFramesToBowl      percentage of frames that the bowler will bowl
     */
    Bowler(double inPercentStrikes, double inPercentSpares, double inPercentOpenFrames, int inFramesToBowl)
    {
        percentStrikes = inPercentStrikes;
        percentSpares = inPercentSpares;
        percentOpenFrames = inPercentOpenFrames;
        framesToBowl = inFramesToBowl;
        selectBallWeight();
    }
    
    /*
     * BowlOneGame
     */
    public void bowlOneGame()
    {   
        double spares = 0,
               strikes = 0;
        
        printBeginningInfo();
        
        for (int i = 0; i <= framesToBowl - 1; i++)
        {   
            if((spares / framesToBowl) < percentSpares)
            {
                if (i < framesToBowl - 1)
                {
                    ballOnes[i] = (int)((Math.random() * 10));
                    ballTwos[i] = 10 - ballOnes[i];
                }
                else
                {
                    ballOnes[i] = (int)((Math.random() * 10));
                    ballTwos[i] = 10 - ballOnes[i];
                    ballThrees[i] = (int)((Math.random() * 10));
                }
                spares++;
            }
            else if((strikes / framesToBowl) < percentStrikes)
            {
                if (i < framesToBowl - 1)
                {
                    ballOnes[i] = 10;
                    ballTwos[i] = 0;
                }
                else if (i == framesToBowl - 1)
                {
                    ballOnes[i] = 10;
                    ballTwos[i] = 10;
                    ballThrees[i] = 10;
                }
                strikes++;
            }
            else
            {
                if (ballWeight < 5 && ballWeight > 0)                   //Rolls the first ball according to weight
                    ballOnes[i] = (int)((Math.random() * 4));
                else if (ballWeight >=5 && ballWeight <= 8)
                    ballOnes[i] = (int)((Math.random() * 4) + 5);
                else if (ballWeight >= 9)
                    ballOnes[i] = (int)((Math.random() * 4) + 6);
                else
                    ballOnes[i] = (int)((Math.random() * 10));
                
                ballTwos[i] = (int)((Math.random() * (10 - ballOnes[i])));
                
                if (ballOnes[i] + ballTwos[i] == 10 && i == framesToBowl - 1)
                    ballThrees[i] =  (int)((Math.random() * 10));
                else
                    ballThrees[i] = 0;
            }
        }
        printGameInfo();
        totalScore = 0;                                              //reinitialize score to 0 for future games
    }
    
    /*
     * setFramesToBowl
     * 
     * @param   numOfFrames     number of frames to bowl
     */
    public void setFramesToBowl(int numOfFrames)
    {
        framesToBowl = numOfFrames;
    }
    
    /*
     * setSparePercentage
     * 
     * @param   newSparePercentage      percent of spares that will be scored
     */
    public void setSparePercentage(double newSparePercentage)
    {
        percentSpares = newSparePercentage;
    }
    
    /*
     * setOpenFramePercentage
     * 
     * @param   newOpenFramePercentage  percent of frames that are not strikes or spares
     */
    public void setOpenFramePercentage(double newOpenFramePercentage)
    {
        percentOpenFrames = newOpenFramePercentage;
    }
    
    /*
     * setStrikePecentage
     * 
     * @param   newStrikePercentage     percent of frames that will be strikes
     */
    public void setStrikePercentage(double newStrikePercentage)
    {
        percentStrikes = newStrikePercentage;
    }
    
    /*
     * scoreFrame
     *
     * @param frame     the frame that will be scored
     */
    public void scoreFrame(int frame)
    {   
        if (ballOnes[frame] == 10)
        {
            if (frame < framesToBowl - 1)
            {
                if (ballOnes[frame + 1] != 10)
                    totalScore += 10 + ballOnes[frame + 1] + ballTwos[frame + 1];
                else
                    totalScore += 10 + ballOnes[frame + 1] + ballOnes[frame + 1];
            }
            else
                totalScore += 10 + ballTwos[frame] + ballThrees[frame];
        }
        else if (ballOnes[frame] + ballTwos[frame] == 10)
        {
            if (frame < framesToBowl - 1)
                totalScore += 10 + ballOnes[frame + 1];
            else
                totalScore += 10 + ballThrees[frame];
        }
        else
        {
            totalScore += ballOnes[frame] + ballTwos[frame];
        }
    }
    
    /*
     * printGameInfo
     */
    public void printGameInfo()
    {
        for (int i = 0; i <= framesToBowl - 1; i++)
        {
            System.out.format("%-6s %d%s", "Frame:", i + 1, ", ");
            
            System.out.format("%s %d%s", "ball 1:", ballOnes[i], ", ");
            
            if (ballOnes[i] != 10 && i != framesToBowl)
                System.out.format("%s %d%s", "ball 2:", ballTwos[i], ", ");
            else if (ballOnes[i] == 10 && i + 1 == framesToBowl)
                System.out.format("%s %d%s", "ball 2:", ballTwos[i], ", ");
                
            if (ballOnes[i] + ballTwos[i] >= 10 && i + 1 == framesToBowl)
                System.out.format("%s %d%s", "ball 3:", ballThrees[i], ", ");
            
            scoreFrame(i);                                                      //scores the current frame
            System.out.format("%s %d %n", "total score =", totalScore);
            
            if(i == framesToBowl - 1)
                System.out.println();
        }    
    }
    
    /*
     * printInfo
     */
    public void printBeginningInfo()
    {
        System.out.format("%-23s %d%n", "Frames in game: ", framesToBowl);
        System.out.format("%-23s %f%n", "Strike percentage: ", percentStrikes);
        System.out.format("%-23s %f%n", "Spare percentage: ", percentSpares);
        System.out.format("%s %f%n%n", "Open frame percentage: ", percentOpenFrames);
    }
    
    public void selectBallWeight()
    {
        Scanner in = new Scanner(System.in);
        
        System.out.println("What would you like your ball weight to be?");
        System.out.println("(Enter 0 if you want the weight of the ball to have no effect)");
        System.out.print("\nBall Weight: ");
        ballWeight = in.nextDouble();
    }
}
