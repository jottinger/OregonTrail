/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;

import byui.cit260.oregonTrail.control.HuntControl;
import byui.cit260.oregonTrail.control.MapControl;
import byui.cit260.oregonTrail.exceptions.MapControlException;
import byui.cit260.oregonTrail.model.Animal;
import byui.cit260.oregonTrail.model.Location;
import byui.cit260.oregonTrail.control.InventoryControl;
import byui.cit260.oregonTrail.model.InventoryType;
import byui.cit260.oregonTrail.exceptions.InventoryControlException;
import byui.cit260.oregonTrail.model.InventoryItem;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import oregonTrail.OregonTrail;

/**
 *
 * @author hannahwilliams
 */
public class HuntView extends View {

    public HuntView() {
        super("\n"
                + "\n----------------------------------------------------"
                + "\n| Animal Menu                                        |"
                + "\n----------------------------------------------------"
                + "\nA - Bison - Bisons are herbivores. They have very poor eyesight but acute hearing and excellent smell."
                + "\nB - Wolf - Wolves are carnivoes. They are territorial with an excellent sense of smell and tracking skills."
                + "\nC - Bear - Bears are omnivores. They can be lazy but vicious if feeling threatened."
                + "\nD - Rabbit - Rabbits are herbivores. They are fast with a great sense of smell."
                + "\nQ - Quit"
                + "\n----------------------------------------------------"
                + "\n"
                + "\nWhich animal would you like to hunt?");

    }

    @Override
    public void display() {
        boolean done = false; //set flag to not done
        do {
            String value = this.getInput();//calls GetMenuOption from this class
            if (value.toUpperCase().equals("Q")) //user wants to quit
            //return; // Returns control to displayNextView() in StartProgramView. (Exit game) TODO: Why does this exit game?
            {
                try {
                    SceneView sceneView = new SceneView();
                    sceneView.display();
                } catch (MapControlException ex) {
                    ErrorView.display(this.getClass().getName(), ex.getMessage());
                    return;
                }
            }
            done = this.doAction(value);

        } while (!done); // repeats the loop if done = false. False value will be returned from doAction() if menuOption is invalid.

    }

    @Override
    public boolean doAction(String value) {
        try {
            try {
                // set activity to done.
                Location location = MapControl.getCurrentLocation();
                location.getScene().setActivityDone(true);
            } catch (MapControlException ex) {
                ErrorView.display(this.getClass().getName(), ex.getMessage());
            }
            
            value = value.toUpperCase();
            Animal animal = null;
            String difficulty;
            boolean done = false;
            
            switch (value) {
                case "A":
                    animal = Animal.Bison;
                    storeAnimal(animal);
                    break;
                case "B":
                    animal = Animal.Wolf;
                    storeAnimal(animal);
                    break;
                case "C":
                    animal = Animal.Bear;
                    storeAnimal(animal);
                    break;
                case "D":
                    animal = Animal.Rabbit;
                    storeAnimal(animal);
                    break;
            }
            
            return false;
            
        } catch (InventoryControlException ex) {
            Logger.getLogger(HuntView.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
   

//Store the animal 
//calculate probability
//execute probability


//if success, calcfoodweight
//add food to inventory
//subtract bullet

//if fail, subtract bullet
    
    
    private void storeAnimal(Animal animal) throws InventoryControlException {
       
        
        HuntControl huntControl = new HuntControl();
        
        String animalChoice = animal.name();
        int startdate = OregonTrail.getCurrentGame().getStartDate();
        int traveldays = OregonTrail.getCurrentGame().getTravelDays();
        
  
        double success = huntControl.calcHuntingSuccessProbability(animalChoice, startdate, traveldays);
       
        
        if (success == 0) {
            String failed = "\nYour hunt was unsuccessful. A bullet has been subtracted from your inventory.";
            this.console.print(failed);
            InventoryControl.subtractFromInventory(InventoryType.Bullets, 1);
            //return to menu
        } else if (success == 1) {
            String successful = "\nYou hunt was successful! A bullet has been subtracted and food added to your inventory.";
            this.console.print(successful);
            //subtract bullets
            InventoryControl.subtractFromInventory(InventoryType.Bullets, 1);
            //calcFood
            int baseweight1 = animal.getBaseWeight();
            InventoryItem[] inventory = OregonTrail.getCurrentGame().getInventory();
            double guide = inventory[InventoryType.Guide.ordinal()].getQuantityInStock();
            int foodWeight = huntControl.calcFoodWeight(baseweight1, (int) guide);
            //addFood
            InventoryControl.addToInventory(InventoryType.Food, foodWeight);
        }

    
    }

}
