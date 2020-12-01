/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.model;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
/**
 *
 * @author hannahwilliams
 */
public class RiverScene extends Scene implements Serializable{
    // class instance variables
    
    private int riverHeight; 
    public String travelChoice; 
    private double safetyWithGuide;
    private boolean activityDone;
    
    // relationships with other classes

    private InventoryItem[] inventory = new InventoryItem[8];
    // default constructor
    
    public RiverScene () {
    }
    
    // methods


    public int getRiverHeight() {
        return riverHeight;
    }

    public void setRiverHeight(int riverHeight) {
        this.riverHeight = riverHeight;
    }

    public double getSafetyWithGuide() {
        return safetyWithGuide;
    }

    public void setSafetyWithGuide(double safetyWithGuide) {
        this.safetyWithGuide = safetyWithGuide;
    }

    public String getTravelChoice() {
        return travelChoice;
    }

    public void setTravelChoice(String travelChoice) {
        this.travelChoice = travelChoice;
    }

    public InventoryItem[] getInventory() {
        return inventory;
    }

    public void setInventory(InventoryItem[] inventory) {
        this.inventory = inventory;
    }

    public boolean isActivityDone() {
        return activityDone;
    }

    public void setActivityDone(boolean activityDone) {
        this.activityDone = activityDone;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + this.riverHeight;
        hash = 13 * hash + Objects.hashCode(this.travelChoice);
        hash = 13 * hash + (int) (Double.doubleToLongBits(this.safetyWithGuide) ^ (Double.doubleToLongBits(this.safetyWithGuide) >>> 32));
        hash = 13 * hash + (this.activityDone ? 1 : 0);
        hash = 13 * hash + Arrays.deepHashCode(this.inventory);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RiverScene other = (RiverScene) obj;
        if (this.riverHeight != other.riverHeight) {
            return false;
        }
        if (Double.doubleToLongBits(this.safetyWithGuide) != Double.doubleToLongBits(other.safetyWithGuide)) {
            return false;
        }
        if (this.activityDone != other.activityDone) {
            return false;
        }
        if (!Objects.equals(this.travelChoice, other.travelChoice)) {
            return false;
        }
        if (!Arrays.deepEquals(this.inventory, other.inventory)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RiverScene{" + "riverHeight=" + riverHeight + ", travelChoice=" + travelChoice + ", safetyWithGuide=" + safetyWithGuide + ", activityDone=" + activityDone + ", inventory=" + inventory + '}';
    }
    

    
}
