package pw.gunnarholwerda;

/**
 * Created by Gunnar on 1/26/2016.
 */
public class Fleet {
    Ship ship1, ship2, ship3, ship4;

    Fleet(Ship ship1, Ship ship2, Ship ship3, Ship ship4) {
        this.ship1 = ship1;
        this.ship2 = ship2;
        this.ship3 = ship3;
        this.ship4 = ship4;
    }

    public void deploy() {
        ship1.deploy();
        ship2.deploy();
        ship3.deploy();
        ship4.deploy();
    }

    public void reFuel() {
        ship1.refuel();
        ship2.refuel();
        ship3.refuel();
        ship4.refuel();
    }

    public void printSummary() {
        System.out.println(ship1);
        System.out.println(ship2);
        System.out.println(ship3);
        System.out.println(ship4);
    }
}
