/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package taxcalculator;

public class TaxCalculator {
    private final double TAX_STRATEGY_A_VALUE = 118000;
    private DefaultStrategy default_strat;
    private TaxStrategy alt_strat;
    
    public TaxCalculator() {
        
    }
    
    public void determineAltStrategy(double salary, double investments, boolean deductions, boolean business_income) {
        default_strat = new DefaultStrategy();
        if (business_income) {
            alt_strat = new TaxStrategyC();
        }
        else if (deductions){
            alt_strat = new TaxStrategyB();
        }
        else if ((salary + investments) > TAX_STRATEGY_A_VALUE){
            alt_strat = new TaxStrategyA();
        }
    }
    
    public void executeStrategies(double salary, double investments) {
        default_strat.execute(salary, investments);
        alt_strat.execute(salary, investments);
    }
    
}
