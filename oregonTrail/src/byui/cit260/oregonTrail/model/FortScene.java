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
 * @author Dresen_HP
 */
public class FortScene extends Scene implements Serializable {
    // class instance variables
    private InventoryItem itemToBuy;
    private double QuantityToBuy;
    
    private InventoryItem[] inventory = new InventoryItem[8];
    
    // default constructor
    
    public FortScene() {
        
    }

    public InventoryItem getItemToBuy() {
        return itemToBuy;
    }

    public void setItemToBuy(InventoryItem itemToBuy) {
        this.itemToBuy = itemToBuy;
    }
    

   

    public InventoryItem[] getInventory() {
        return inventory;
    }

    public void setInventory(InventoryItem[] inventory) {
        this.inventory = inventory;
    }

    public double getQuantityToBuy() {
        return QuantityToBuy;
    }

    public void setQuantityToBuy(double QuantityToBuy) {
        this.QuantityToBuy = QuantityToBuy;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.itemToBuy);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.QuantityToBuy) ^ (Double.doubleToLongBits(this.QuantityToBuy) >>> 32));
        hash = 79 * hash + Arrays.deepHashCode(this.inventory);
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
        final FortScene other = (FortScene) obj;
        if (Double.doubleToLongBits(this.QuantityToBuy) != Double.doubleToLongBits(other.QuantityToBuy)) {
            return false;
        }
        if (!Objects.equals(this.itemToBuy, other.itemToBuy)) {
            return false;
        }
        if (!Arrays.deepEquals(this.inventory, other.inventory)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FortScene{" + "itemToBuy=" + itemToBuy + ", QuantityToBuy=" + QuantityToBuy + ", inventory=" + inventory + '}';
    }

    
    
}
