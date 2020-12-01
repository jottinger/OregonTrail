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
public enum Occupation {
    Farmer("Farmer", "Skilled at growing food. Receives a bonus upon reaching Oregon."),
   Blacksmith("Blacksmith", "Good at fixing things. Wagon will not break down."),
   Merchant("Merchant", "Drives a hard bargain. Starts game with an extra $200.");
   
   // class instance variables
    private final String name;
    private final String description;
    
    Occupation(String name, String description) {
        this.name = name;
        this.description = description;
        
    }   

    public static Occupation getFarmer() {
        return Farmer;
    }

    public static Occupation getBlacksmith() {
        return Blacksmith;
    }

    public static Occupation getMerchant() {
        return Merchant;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Occupation{" + "name=" + name + ", description=" + description + '}';
    }
    
    
}
