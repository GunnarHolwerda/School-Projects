
/**
 * Write a description of class MondrianFrame here.
 * 
 * @author Brenan
 * @version 16 Jan 2011
 */

import java.awt.*;
import javax.swing.*;

public class MondrianPanel extends JPanel
{
    // instance variables - replace the example below with your own
    private int size = 600;
    private final int MAX_RECTANGLES = 100;

    /**
     * Constructor for objects of class MondrianFrame
     */
    public MondrianPanel()
    {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(size, size));
    }
    
    @Override
    public void paint(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        for (int i = 0; i < MAX_RECTANGLES; i++)
        {
            int startX = randCoord();
            int startY = randCoord();
            int endX = randCoord();
            int endY = randCoord();
            
            //Creates the border for the color square
            g.setColor(Color.BLACK);
            g.fillRect(startX, startY , endX + 10, endY + 10);
            
            //Creates colored square to go in the border
            g.setColor(getRandomColor());
            g.fillRect(startX + 10, startY + 10, endX - 10, endY - 10);
        }
    }
    
    /*
     * Creates a random color
     * 
     * @return  Color object that is randomly generated
     */
    private Color getRandomColor()
    {
        float redValue = (float)(Math.random());
        float greenValue = (float)(Math.random());
        float blueValue = (float)(Math.random());
        
        return new Color(redValue, greenValue, blueValue);
    }
    
    /*
     * Creates a random integer within the panel
     * 
     * @return  a number to be used as a coordinate when painting a rectangle
     */
    private int randCoord()
    {
        return (int)(Math.random() * size - 100);
    }

}
