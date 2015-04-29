/**
 * Partial solution to Program 4 for open frames and spares (but no strikes).
 *
 * @author John Paxton
 * @version October 29, 2012
 */

public class Bowler
{
    private double percentStrikes;    // percentage of time to bowl a strike
    private double percentSpares;     // percentage of time to bowl a spare
    private double percentOpen;       // percentage of time for open frame
    private int frames;               // number of frames to bowl
    
    private int currentScore;         // bowler's current score
    private boolean buildingOnSpare;  // was the previous frame a spare:
    private boolean buildingOnStrike;
    private double strikes = 0, spares = 0;
    private int ball_1;
    private int ball_2;
    private int ball_3;               // the third roll from the previous frame

    public Bowler (double percentStrikes, double percentSpares, double percentOpen, int frames)
    {
        this.percentStrikes = percentStrikes;
        this.percentSpares = percentSpares;
        this.percentOpen = percentOpen;
        this.frames = frames;
    }

    public void setFramesToBowl (int inFrames)
    {
        frames = inFrames;
    }

    public void setStrikePercentage (double percentage)
    {
        percentStrikes = percentage;
    }

    public void setSparePercentage (double percentage)
    {
        percentSpares = percentage;
    }

    public void setOpenFramePercentage (double percentage)
    {
        percentOpen = percentage;
    }
    
    /*
     * bowlOneGame
     * 
     */
    public void bowlOneGame()
    {
        currentScore = 0;
        buildingOnSpare = false;
        
        System.out.format("%n%-23s%d%n", "Frames in game: ", frames);
        System.out.format("%-23s%.2f%n", "Strike percentage: ", percentStrikes);
        System.out.format("%-23s%.2f%n", "Spare percentage: ", percentSpares);
        System.out.format("%s%.2f%n%n", "Open frame percentage: ", percentOpen);
        
        for (int frame = 1; frame <= frames; frame++)
        {
            bowlOneFrame(frame);
        }
        spares = 0;
        strikes = 0;
    }
    
    /*
     * bowlOneFrame
     * 
     * @param       frame           the current frame
     */
    private void bowlOneFrame(int frame)
    { 
        if (buildingOnStrike)
        {
            ball_1 = ball_2;
            if(ball_1 == 10)
                strikes++;
            ball_2 = ball_3;
            if (frame == frames)
                strikes--;
            ball_3 = firstRoll();
            buildingOnStrike = false;
        }
        else if (buildingOnSpare)
        {
            ball_1 = ball_3;
            ball_2 = secondRoll(ball_1);
            if (ball_1 + ball_2 == 10)
                spares++;
            buildingOnSpare = false;
        }
        else
        {
            ball_1 = firstRoll();
            if (ball_1 == 10)
                strikes++;
            ball_2 = secondRoll(ball_1);
            if (ball_1 + ball_2 == 10)
                spares++;
            ball_3 = 0;
        }
        
        if (buildingOnStrike)
        {
            ball_3 = secondRoll(ball_2);
        }
        else if (buildingOnSpare)
        {
            ball_3 = firstRoll();
        }
        
        this.currentScore += ball_1 + ball_2 + ball_3;
        
        if (ball_1 == 10 && (frame != frames))
        {
            System.out.print("Frame: " + frame + ", ball 1 = " + ball_1 + ", ball 2 = " + 0);
            System.out.println(", total score = " + currentScore);
        }
        else
        {
            System.out.print("Frame: " + frame + ", ball 1 = " + ball_1 + ", ball 2 = " + ball_2);
            if ((frame == frames) && (ball_1 + ball_2 >= 10))
            {
                System.out.print(", ball 3 = " + ball_3);
            }
            System.out.println(", total score = " + currentScore);
        }
    }
    
    /*
     * firstRoll
     * 
     * @return     the value of the first ball in a frame
     */
    private int firstRoll()
    {
        if ((strikes / frames) < percentStrikes)
        {
            return 10;
        }
        else
            return (int) (Math.random() * 10);
    }
    
    /*
     * secondRoll
     * 
     * @param   ball_1      the ball prior to the current ball being assigned 
     * 
     * @return              the value of the second roll of the frame 
     */
    private int secondRoll (int ball_1)
    {
        if (ball_1 == 10)                                       //computes if a strike
        {
            buildingOnStrike = true;
            return firstRoll();
        }
        else if ((spares / frames) < percentSpares)     //computes a spare
        {
            buildingOnSpare = true;
            return (10 - ball_1);
        }
        else                                                    //computes an open frame
        {
            return (int) (Math.random() * (10 - ball_1));
        }
    }
}