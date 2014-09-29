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
    private double salary, investments, deductions;
    
    
    public TaxStrategyB(double salary, double investments) {
        this.salary = salary;
        this.investments = investments;
        
        System.out.println("You are qualified for Tax Strategy B because you have deductions!");
        System.out.print("Enter total deductions: ");
        this.deductions = in.nextDouble();
    }
    
    public void execute(){ 
        System.out.print("We are executing Tax Strategy B...\n");
        double tax_to_be_paid = ((salary + investments) - deductions) * TAX_RATE;
        System.out.printf("With a salary of: $%.2f, and investments of $%.2f you are to pay taxes in total of: $%.2f\n", salary, investments, tax_to_be_paid);
    }
}
