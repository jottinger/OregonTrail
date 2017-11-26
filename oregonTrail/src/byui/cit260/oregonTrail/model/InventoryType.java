/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.model;

/**
 *
 * @author Dresen_HP
 */
public enum InventoryType {
    Bullets("Bullets - 10 count",1),
    Clothing("Clothing - 1 set", 20),
    Food("Food - 20 lbs.", 5),
    Guide("Guide - 1 week", 50),
    Medicine("Medicine - 1 bottle", 10),
    Oxen("Oxen - 1 team", 40),
    WagonWheel("Wagon wheel - 1 wheel", 10),
    Money("Money in dollars", 1);
    
    
    
    private final int cost;
    private String name;
    // constructor function
    private InventoryType(String name, int cost){
        this.cost = cost;
        
    }
    
    
    public String getName() {
        return name;
    }
    public int getCost() {
        return cost;
    }
    
}
