/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.model;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.awt.Point;
import java.util.Arrays;
/**
 *
 * @author jones-jordan
 */
public class BarterScene extends Scene implements Serializable {
    // class instance variables
    private InventoryItem itemsToTrade;
    private double QuantityToTrade;
    private InventoryItem itemDesired;
    
    // relationships with other classes
    private InventoryItem[] inventory = new InventoryItem[8];
    
    // default constructor

    public BarterScene() {
    }

    public InventoryItem getItemsToTrade() {
        return itemsToTrade;
    }

    public void setItemsToTrade(InventoryItem itemsToTrade) {
        this.itemsToTrade = itemsToTrade;
    }

    public double getQuantityToTrade() {
        return QuantityToTrade;
    }

    public void setQuantityToTrade(double QuantityToTrade) {
        this.QuantityToTrade = QuantityToTrade;
    }


    public InventoryItem getItemDesired() {
        return itemDesired;
    }

    public void setItemDesired(InventoryItem itemDesired) {
        this.itemDesired = itemDesired;
    }

    public InventoryItem[] getInventory() {
        return inventory;
    }

    public void setInventory(InventoryItem[] inventory) {
        this.inventory = inventory;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.itemsToTrade);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.QuantityToTrade) ^ (Double.doubleToLongBits(this.QuantityToTrade) >>> 32));
        hash = 59 * hash + Objects.hashCode(this.itemDesired);
        hash = 59 * hash + Arrays.deepHashCode(this.inventory);
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
        final BarterScene other = (BarterScene) obj;
        if (this.QuantityToTrade != other.QuantityToTrade) {
            return false;
        }
        if (!Objects.equals(this.itemsToTrade, other.itemsToTrade)) {
            return false;
        }
        if (!Objects.equals(this.itemDesired, other.itemDesired)) {
            return false;
        }
        if (!Arrays.deepEquals(this.inventory, other.inventory)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BarterScene{" + "itemsToTrade=" + itemsToTrade + ", QuantityToTrade=" + QuantityToTrade + ", itemDesired=" + itemDesired + ", inventory=" + inventory + '}';
    }






    
}