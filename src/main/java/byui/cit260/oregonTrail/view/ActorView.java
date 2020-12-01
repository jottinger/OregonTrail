/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;
import byui.cit260.oregonTrail.control.GameControl;
import byui.cit260.oregonTrail.model.Actor;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import oregonTrail.OregonTrail;

/**
 *
 * @author hannahwilliams
 */
public class ActorView extends View {
    //class instance variables
    
    //constructor method
    public ActorView() {
        super("\n"
                + "\nWould you like to see the list of actors?"
                + "\n----------------------------------------------------"
                + "\nY"
                + "\nN");
    }
    
    @Override
    public void display() {  //called from main() in OregonTrail.java
            boolean done = false; // set flag to not done
            do {
                //prompt for and get player's name
                String value = this.getInput(); // calls getPlayersName() from this class, stores in string playersName
                GameMenuView gameMenuView = new GameMenuView();
                if (value.toUpperCase().equals("Q")) // user wants to quit
                    gameMenuView.display();
                    
                 
                //do the requested action and display the next view
                done = this.doAction(value);// Calls doAction()in this class and passes in name. Return value changes boolean to true to exit do while loop.
            } while (!done);
    }      
    @Override
    public boolean doAction(String value) {
        value = value.toUpperCase();
        
        switch (value) {
            case "Y":
                PrintActorView printActorView = new PrintActorView();
                printActorView.display();
                break;
            case "N":
                TalkToLocalsView talkToLocals = new TalkToLocalsView();
                talkToLocals.display();
                break;
            default:
                ErrorView.display(this.getClass().getName(), "*** Error: invalid choice entered. Try again. ***");
        }
        return false;
    }
    
    private void printList() {
         
        String filepath = this.getFilePath();
        String list = "\n"
               +"\n----------------------------------------------------"              
                +"\n List of Actors                                    |"
                +"\n----------------------------------------------------"
                +"\n   Name, Location   "
                +"\n";
        List<Actor> actor = OregonTrail.getCurrentGame().getActors();
            for (Actor stop : actor) {
                list += "\n" + stop.getName() + stop.getDescription();
            }
            this.console.print(list);
            try {
                printActorView(list, filepath);
            }
            catch (Exception ex) {
                ErrorView.display(this.getClass().getName(), ex.getMessage());
            }
            display();
        }
    
    private String getFilePath() {
        String value = "";
        boolean valid = false;
        try {
            while (!valid) {
                value = keyboard.readLine();
                value = value.trim();
                
                if (value.length() < 1) {
                    ErrorView.display(this.getClass().getName(), "\nInvalid value: value cannot be blank"
                          + "\n Enter game file path");
                    continue;
                }
                break;
            }
        }catch (IOException ex) {
            ErrorView.display(this.getClass().getName(), "Error reading input: " + ex.getMessage());
    } 
      return value;  
}
    private void printActorView(String list, String filePath) {
        try (PrintWriter out = new PrintWriter(filePath)) {
            out.println("\n\n         ACTOR LIST          \n");
            out.printf("%n%-20s%20s", "Name", "Description");
            out.printf("%n%-20s%20s", "------", "-----------");
          
        Actor[] actor = Actor.values();   
        for (Actor stop : actor) {
            out.printf("%n%-20s%20s", stop.getName(), stop.getDescription());
            }
        } 
         catch (FileNotFoundException ex)  {
            Logger.getLogger(ActorView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
