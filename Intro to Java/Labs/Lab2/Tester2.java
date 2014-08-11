
/**
 * Write a description of class Tester2 here.
 * 
 * @author Gunnar Holwerda
 * @version 
 */
public class Tester2 extends junit.framework.TestCase
{
    public void testStock()
    {
        Stock microsoft = new Stock("MSFT", 100, 30.95);
        Stock apple = new Stock("AAPL", 10, 680.44);
    
        assertEquals("AAPL", apple.getName());
        assertEquals(680.44, apple.getPrice());
        assertEquals(10, apple.getShares());
        assertEquals(6804.40, apple.getValue(), 1e-2);
    
        assertEquals("MSFT", microsoft.getName());
        assertEquals(30.95, microsoft.getPrice());
        assertEquals(100, microsoft.getShares());
        assertEquals(3095.00, microsoft.getValue());
    }
}
