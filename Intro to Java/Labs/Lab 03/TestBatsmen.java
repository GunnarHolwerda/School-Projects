/**
 * TestBatsmen performs some junit testing for Lab 3.
 * 
 * @author John Paxton
 * @version September 18, 2012
 */

public class TestBatsmen extends junit.framework.TestCase
{
    public void testBatsmen()
    {
        Batsmen jason = new Batsmen("Jason", 105, 5, 25, 10, 200);
        assertEquals(.250, jason.average(), .0001);
        assertEquals(12.5, jason.scoringRate(), .0001);

        Batsmen kp = new Batsmen("Kevin Peterson", 97, 4, 4647, 226, 7404);
        assertEquals(49.97, kp.average(), .005);
        assertEquals(62.76, kp.scoringRate(), .005);
    }
}