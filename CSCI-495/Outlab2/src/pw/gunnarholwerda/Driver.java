package pw.gunnarholwerda;

/**
 * Driver for Outlab2.
 */
public class Driver
{
    public static void main(String[] args)
    {
        //Creating 4 instances of Ship
        Ship ship1 = new Ship("Carrier", 150);
        Ship ship2 = new Ship("Anti-Submarine", 35);
        Ship ship3 = new Ship("Patrol", 22);
        Ship ship4 = new Ship("Destroyer", 83);

        //Creating instance of Fleet
        Fleet fleet1 = new Fleet(ship1, ship2, ship3, ship4);

        //Deploying the fleet twice
        fleet1.deploy();
        fleet1.deploy();

        //Refuel the fleet once
        fleet1.reFuel();

        //Print summary
        fleet1.printSummary();
    }
}