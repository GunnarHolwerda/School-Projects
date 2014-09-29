/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package taxcalculator;

/**
 *
 * @author Gunnar
 */
public class DefaultStrategy implements TaxStrategy{
    private final double TAX_RATE = 0.28;
    
    @Override
    public void execute(double salary, double investments) {
        System.out.print("We are executing the default strategy...\n");
        double tax_to_be_paid = (salary + investments) * TAX_RATE;
        System.out.printf("With a salary of: $%.2f, and investments of $%.2f you are to pay taxes in total of: $%.2f\n", salary, investments, tax_to_be_paid);
    }
    
}
