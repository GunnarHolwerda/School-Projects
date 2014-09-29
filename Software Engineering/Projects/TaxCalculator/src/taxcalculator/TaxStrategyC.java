/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package taxcalculator;

import java.util.Scanner;

public class TaxStrategyC implements TaxStrategy {
    Scanner in = new Scanner(System.in);
    private final double TAX_RATE = 0.30;
    private double salary, investments, expenses;
    
    
    public TaxStrategyC(double salary, double investments){
        this.salary = salary;
        this.investments = investments;
        
        System.out.println("You are qualified for Tax Strategy C because you have your own business!");
        System.out.print("Enter total business expenses: ");
        this.expenses = in.nextDouble();
    }
    
    @Override
    public void execute() {
        System.out.print("We are executing Tax Strategy C...\n");
        double tax_to_be_paid = ((salary + investments) - expenses) * TAX_RATE;
        System.out.printf("With a salary of: $%.2f, and investments of $%.2f you are to pay taxes in total of: $%.2f\n", salary, investments, tax_to_be_paid);
    }

}
