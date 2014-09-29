/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package taxcalculator;

import java.util.Scanner;

public class TaxStrategyB implements TaxStrategy {
    Scanner in = new Scanner(System.in);
    private final double TAX_RATE = 0.28;
    
    public void execute(double salary, double investments){ 
        System.out.println("You are qualified for Tax Strategy B because you have deductions!");
        System.out.print("Enter total deductions: ");
        double deductions = in.nextDouble();
        
        System.out.print("We are executing Tax Strategy B...\n");
        double tax_to_be_paid = ((salary + investments) - deductions) * TAX_RATE;
        System.out.printf("With a salary of: $%.2f, and investments of $%.2f you are to pay taxes in total of: $%.2f\n", salary, investments, tax_to_be_paid);
    }
}
