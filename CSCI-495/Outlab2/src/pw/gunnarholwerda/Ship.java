package pw.gunnarholwerda;

public class Ship {

    private String name;
    private int currentFuel;
    private int maxFuelCapacity;
    private int totalFuelConsumed;

    Ship(String name, int fuelCapacity) {
        this.totalFuelConsumed = 0;
        this.name = name;
        this.maxFuelCapacity = fuelCapacity;
        this.currentFuel = this.maxFuelCapacity;
    }

    public void refuel() {
        this.currentFuel = this.maxFuelCapacity;
    }

    public void deploy() {
        this.totalFuelConsumed += this.currentFuel/2;
        this.currentFuel /= 2;
    }

    public String toString() {
        return this.name + " has consumed " + this.totalFuelConsumed + " units of fuel over its lifetime.";
    }
}
