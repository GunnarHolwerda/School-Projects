/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package taxcalculator;

public class TaxStrategyA implements TaxStrategy {
    private final double TAX_RATE = 0.3;
    private double salary, investments;
    
    public TaxStrategyA (double salary, double investments) {
        this.salary = salary;
        this.investments = investments;
    }
        
    @Override
    public void execute() {
        System.out.print("We are executing Tax Strategy A...\n");
        double tax_to_be_paid = (salary + investments) * TAX_RATE;
        System.out.printf("With a salary of: $%.2f, and investments of $%.2f you are to pay taxes in total of: $%.2f\n", salary, investments, tax_to_be_paid);
    }
}
