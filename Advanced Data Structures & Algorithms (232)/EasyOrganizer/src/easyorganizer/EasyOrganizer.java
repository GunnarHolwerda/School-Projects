/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package easyorganizer;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;


/**
 *
 * @author Gunnar
 */
public class EasyOrganizer extends JFrame {

    
    EasyOrganizer(){
        super("EasyOrganizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        contentPane.setLayout(new BorderLayout());
        
        
        
        setVisible(true);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EasyOrganizer wow = new EasyOrganizer();
    }
    
}
