/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package volumeconvertergui;

/**
 *
 * @author Gunnar
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VolumeConverterGUI extends JFrame
{
    
    private JTextField gallonsField;
    private JTextField litresField;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        JFrame frame = new VolumeConverterGUI();
        frame.setVisible(true);
    }
    
    public VolumeConverterGUI()
    {
        super("Liquid Volume Converter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(2, 2));
        
        //Setting up the Gallons UI
        contentPane.add(new JLabel("Gallons"));
        gallonsField = new JTextField(10);
        gallonsField.addActionListener(new NewGallonsValue());
        contentPane.add(gallonsField);
        
        //Setting up the Litres UI
        contentPane.add(new JLabel("Litres"));
        litresField = new JTextField(10);
        litresField.addActionListener(new NewLitresValue());
        contentPane.add(litresField);
        
        //Sizes the frame to fit
        pack();
    }
    
    //Inner Class
    /*Class to respond to new Gallons value. */
    public class NewGallonsValue implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                double gallonsValue = Double.parseDouble(gallonsField.getText());
                double litresValue = VolumeConverter.toLitres(gallonsValue);
                litresField.setText(Double.toString(litresValue));
            }
            catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(null, "Invalid Number Format", "", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public class NewLitresValue implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                double litresValue = Double.parseDouble(litresField.getText());
                double gallonsValue = VolumeConverter.toGallons(litresValue);
                gallonsField.setText(Double.toString(gallonsValue));
            }
            catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(null, "Invalid Number Format", "", JOptionPane.ERROR_MESSAGE);
            }    
        }
    }
}
