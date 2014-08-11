import javax.swing.*;
import java.awt.*;

import java.awt.event.*;

/**
 * Write a description of class SweeperPanel here.
 * 
 * @author Gunnar Holwerda & Josh Moellenkamp
 * @version Jan 21
 */
public class SweeperPanel extends JPanel
{
    private Minesweeper mineGame;
    private Sound sound;
    private int size;               //size of the board, dependent on difficulty
    private int numOfMines;         //number of mines, dependent on difficulty


    /**
     * Constructor for objects of class SweeperPanel
     */
    public SweeperPanel(Minesweeper mineGame1)
    {
        mineGame = mineGame1;
        size = mineGame.getSizeofBoard();
        numOfMines = mineGame.getNumOfMines();
        
        setPreferredSize(new Dimension((size * 25) - size/2, (size * 25) - size/2));
        addMouseListener(new MineMouseListener());
        
        JOptionPane.showMessageDialog(this, "Welcome to Minesweeper!"
                + "\nLeft click to clear a square and right click to flag"
                + " the square.");
        
        
        sound = new Sound();
        repaint();
    }

    @Override
    public void paint(Graphics g)
    {
        g.setColor(Color.darkGray);
        g.fillRect(0, 0, size*25, size*25);

        Font f = new Font("Times", Font.PLAIN, 15);
        g.setFont(f);
        FontMetrics fm = g.getFontMetrics();
        
        int a = fm.getAscent();
        int h = fm.getHeight();           
        
        //diplay known squares, flags, and mines
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++){
                if (mineGame.getSquare(i, j).getKnown() == true && mineGame.getSquare(i, j).getContents() == false) {
                     g.setColor(Color.lightGray);
                     g.fillRect((i * 25), (j * 25), 25, 25);
                     g.setColor(Color.black);
                     int curSquare = mineGame.getSquare(i, j).getNumToDisplay();
                     String curSqStr = Integer.toString(curSquare);
                     int w = fm.stringWidth(curSqStr);
                     g.drawString(curSqStr, 25 * i + 12 - w / 2, 25 * j + 12 + a - h / 2);
                     
                }
                if (mineGame.getSquare(i, j).getKnown() == true && mineGame.getSquare(i, j).getContents() == true){
                     g.setColor(Color.lightGray);
                     g.fillRect((i*25),(j*25),25,25);
                     g.setColor(Color.BLUE);
                     String curSqStr = "+";
                     int w = fm.stringWidth(curSqStr);
                     g.drawString(curSqStr, 25 * i + 12 - w / 2, 25 * j + 12 + a - h / 2);
                }
                if(mineGame.getSquare(i, j).getToggleFlag() == true) {
                    g.setColor(Color.red);
                    String curSqStr = Character.toString('X');
                    int w = fm.stringWidth(curSqStr);
                    g.drawString(curSqStr, 25*i + 12 - w/2, 25*j + 12 + a - h/2); 
                }
            }
        }
        
        //draw squares
        for(int i = 0; i < (size*25); i++) { 
            g.setColor(Color.BLACK);
            g.drawLine(0,(i*25),(25*size),(i*25));
            g.drawLine((i*25),0,(i*25),(25*size));           
        }
        
    }
        
        

       
        

     //INNER CLASS for a Mouse events
    private class MineMouseListener implements MouseListener {
        int xCoor = 0,
            yCoor = 0;
        
        @Override
        public void mousePressed(MouseEvent e) {
            
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
            
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
        
        }
       
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                sound.playClick();
                xCoor = e.getX() / 25;      //squares are size 25
                yCoor = e.getY() / 25;
                mineGame.getSquare(xCoor, yCoor).setKnown(true); 
                mineGame.getSquare(xCoor,yCoor).setToggleFlag(false);
                mineGame.checkSquare(xCoor, yCoor);
                if (mineGame.checkEndGame()){
                    mineGame.playEndScenario();
                }
                repaint();
            }
            else if (e.getButton() == MouseEvent.BUTTON3) {
                sound.playGong();
                int xCoor = e.getX() / 25;
                int yCoor = e.getY() / 25;
                if (mineGame.getSquare(xCoor, yCoor).getToggleFlag() == false){
                    mineGame.getSquare(xCoor, yCoor).setToggleFlag(true);
            }
            else {
                mineGame.getSquare(xCoor, yCoor).setToggleFlag(false);                    
                }
                repaint();
            }
        }
    }
}

              



