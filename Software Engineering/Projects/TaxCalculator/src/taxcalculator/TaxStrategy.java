package taxcalculator;

/**
 *
 * @author Gunnar
 */
public interface TaxStrategy {
    public void execute(double salary, double investments);
}
