/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gunnar
 */
public class Food{
    
    String name;
    String type;
    double price = 0.00;
    
    public Food(String name, String type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;     
    }
    
    public String getName(){
        return name;
    }
    
    public String getType(){
        return type;
    }
    
    public double getPrice(){
        return price;
    }
}
