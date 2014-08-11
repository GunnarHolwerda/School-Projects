
/**
 * Write a description of class MondrianFrame here.
 * 
 * @author Brenan
 * @version 16 Jan 2011
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MondrianFrame extends JFrame
{
    // instance variables - replace the example below with your own
    JButton refreshButton;
    /**
     * Constructor for objects of class MondrianFrame
     */
    public MondrianFrame()
    {
        // initialise instance variables
        super("Mondrian Example");
        
        //Sets up the JPanel
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new MondrianPanel(), BorderLayout.CENTER);
        
        //Sets up the JButton
        refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new RefreshRectangles());
        getContentPane().add(refreshButton, BorderLayout.SOUTH);
        
        //Packs and sets visible to true
        pack();
        setVisible(true); // Show the JFrame.
    }
    
    private class RefreshRectangles implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            repaint();
        }
    }

}
