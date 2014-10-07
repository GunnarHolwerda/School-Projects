/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package taxcalculator;

import java.util.Scanner;

/**
 *
 * @author Gunnar
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the Tax Calculator. "
                + "I will be your accountant today.\n");
        
        TaxCalculator calc = new TaxCalculator();
        Scanner in = new Scanner(System.in);
        
        System.out.print("Please enter your salary: ");
        double salary = in.nextDouble();
        System.out.print("Please enter your investments value: ");
        double investments = in.nextDouble();
        System.out.print("Do you have deductions? (Yes or No): ");
        String temp = in.next();
        
        boolean deductions;
        if (temp.toUpperCase().equals("YES")) {
            deductions = true;
        }
        else {
            deductions = false;
        }
        
        System.out.print("Does your income come from your own business?"
                + " (Yes or No): ");
        temp = in.next();
        boolean business_income;
        if (temp.toUpperCase().equals("YES")) {
            business_income = true;
        }
        else {
            business_income = false;
        }
        
        calc.determineAltStrategy(salary, investments, deductions, business_income);
        calc.executeStrategies(salary, investments);    
    }
    
}
