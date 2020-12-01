/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
/**
 *
 * @author hannahwilliams
 */
public class HuntingScene extends Scene implements Serializable {
  // class instance variables
    private Animal animal1; 
    private Animal animal2;
    private int bonusWithGuide; 
    
    // relationships with other classes
    
    private Animal[] animal = new Animal[4];

    private InventoryItem[] inventory = new InventoryItem[8];
    
    // default constructor

    public HuntingScene () {
    }

    public Animal getAnimal1() {
        return animal1;
    }

    public void setAnimal1(Animal animal1) {
        this.animal1 = animal1;
    }

    public Animal getAnimal2() {
        return animal2;
    }

    public void setAnimal2(Animal animal2) {
        this.animal2 = animal2;
    }

    public int getBonusWithGuide() {
        return bonusWithGuide;
    }

    public void setBonusWithGuide(int bonusWithGuide) {
        this.bonusWithGuide = bonusWithGuide;
    }

    public Animal[] getAnimal() {
        return animal;
    }

    public void setAnimal(Animal[] animal) {
        this.animal = animal;
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
        hash = 43 * hash + Objects.hashCode(this.animal1);
        hash = 43 * hash + Objects.hashCode(this.animal2);
        hash = 43 * hash + this.bonusWithGuide;
        hash = 43 * hash + Objects.hashCode(this.animal);
        hash = 43 * hash + Arrays.deepHashCode(this.inventory);
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
        final HuntingScene other = (HuntingScene) obj;
        if (this.bonusWithGuide != other.bonusWithGuide) {
            return false;
        }
        if (this.animal1 != other.animal1) {
            return false;
        }
        if (this.animal2 != other.animal2) {
            return false;
        }
        if (!Objects.equals(this.animal, other.animal)) {
            return false;
        }
        if (!Arrays.deepEquals(this.inventory, other.inventory)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HuntingScene{" + "animal1=" + animal1 + ", animal2=" + animal2 + ", bonusWithGuide=" + bonusWithGuide + ", animal=" + animal + ", inventory=" + inventory + '}';
    }

    


    
}
