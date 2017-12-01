/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;

import byui.cit260.oregonTrail.control.HuntControl;
import byui.cit260.oregonTrail.model.Animal;
import java.util.Scanner;
import oregonTrail.OregonTrail;

/**
 *
 * @author hannahwilliams
 */
public class HuntView extends View{
    




    
    public HuntView() {
        super("\n"
                    +"\n----------------------------------------------------"
                    +"\n| Animal Menu                                        |"
                    +"\n----------------------------------------------------"
                    +"\nA - Bison - Bisons are herbivores. They have very poor eyesight but acute hearing and excellent smell."
                    +"\nB - Wolf - Wolves are carnivoes. They are territorial with an excellent sense of smell and tracking skills."
                    +"\nC - Bear - Bears are omnivores. They can be lazy but vicious if feeling threatened."
                    +"\nD - Rabbit - Rabbits are herbivores. They are fast with a great sense of smell."
                    +"\nQ - Quit"
                    +"\n----------------------------------------------------"
                    +"\n"
                    +"\nWhich animal would you like to hunt?");
        

        
        
 
        

       
    }
    @Override
    public void display() {
        boolean done = false; //set flag to not done
        do {
            String value = this.getInput();//calls GetMenuOption from this class
            if (value.toUpperCase().equals("Q")) //user wants to quit
                return; // Returns control to displayNextView() in StartProgramView. (Exit game) TODO: Why does this exit game?
           
            done = this.doAction(value);
 
            
        
        } while (!done); // repeats the loop if done = false. False value will be returned from doAction() if menuOption is invalid.
    
}


 
       @Override
       public boolean doAction(String value) {
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
        
    }
       // save animal choice
       // figure probability
       // subtract bullet from inventory
       // if success then calcFoodWeight 
       // add food to inventory
       // else You failed message.
       // hunting again if yes, then call display
       // else quit to the game menu
       

    private void storeAnimal(Animal animal) {
            HuntControl huntControl = new HuntControl();
            String animalChoice = animal.name();
            String difficulty = animal.getDifficulty();
            int startdate = OregonTrail.getCurrentGame().getStartDate();
            int traveldays = OregonTrail.getCurrentGame().getTravelDays();
            double successProbability = huntControl.calcHuntingSuccessProbability(difficulty, startdate, traveldays);
            String probability = "\nYour hunt success probability is " + successProbability;
            this.console.print(probability);
            String success = "\n You successfully shot the " + animalChoice + "! Would you like to hunt again?";
            String failure = "\n You failed to shoot the " + animalChoice + ". Would you like to hunt again?";
            // TODO: Fix the rest of this
    }
    
}
