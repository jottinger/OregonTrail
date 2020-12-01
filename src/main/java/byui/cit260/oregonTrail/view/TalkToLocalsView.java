/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;

import byui.cit260.oregonTrail.control.GameControl;
import byui.cit260.oregonTrail.control.MapControl;
import byui.cit260.oregonTrail.exceptions.GameControlException;
import byui.cit260.oregonTrail.exceptions.MapControlException;
import byui.cit260.oregonTrail.model.Actor;
import byui.cit260.oregonTrail.model.CharacterDialog;
import byui.cit260.oregonTrail.model.Location;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import oregonTrail.OregonTrail;


/**
 *
 * @author hannahwilliams
 */
public class TalkToLocalsView extends View{
    public TalkToLocalsView() {
    
    super("\n"
                    +"\n----------------------------------------------------"
                    +"\n| Character Menu                                    |"
                    +"\n----------------------------------------------------"
                    +"\nA - Settler"
                    +"\nB - Pioneer"
                    +"\nC - Trapper"
                    +"\nD - Soldier"
                    +"\nE - Clerk"
                    +"\nF - Shoshone"
                    +"\nG - Cayusa"
                    +"\nH - Guide"
                   // +"\nI - Wife"
                    //+"\nJ - Son"
                    //+"\nK - Daughter"
                    +"\nV - View information about Characters"
                    +"\nP - Print information about Characters"
                    +"\nQ - Quit"
                    +"\n----------------------------------------------------"
                    + "\nWho would you like to speak to?");

    
    }
    @Override
    public void display() {  //called from main() in OregonTrail.java
            boolean done = false; // set flag to not done
            do {
                //prompt for and get player's name
                String value = this.getInput(); // calls getPlayersName() from this class, stores in string playersName

                if (value.toUpperCase().equals("Q")) // user wants to quit
                    try {
                    SceneView sceneView = new SceneView();
                    sceneView.display();
                    } catch (MapControlException ex) {
                        ErrorView.display(this.getClass().getName(), ex.getMessage());
                    }
                 
                //do the requested action and display the next view
                done = this.doAction(value);// Calls doAction()in this class and passes in name. Return value changes boolean to true to exit do while loop.
            } while (!done);
    }

    @Override
    public boolean doAction(String choice) {
        choice = choice.toUpperCase();
        try {
            // set activity to done.
            Location location = MapControl.getCurrentLocation();
            location.getScene().setActivityDone(true);
        } catch (MapControlException ex) {
            ErrorView.display(this.getClass().getName(), ex.getMessage());
        }
        Actor actor;
        
        switch (choice) {
            case "A":
                actor = Actor.Settler;
                this.talkToActor(actor);
            case "B":
                actor = Actor.Pioneer;
                this.talkToActor(actor);
                break;
            case "C":
                actor = Actor.Trapper;
                this.talkToActor(actor);
                break;
            case "D":
                actor = Actor.Soldier;
                this.talkToActor(actor);
                break;
            case "E":
                actor = Actor.Clerk;
                this.talkToActor(actor);
                break;
            case "F":
                actor = Actor.Shoshone;
                this.talkToActor(actor);
                break;
            case "G":
                actor = Actor.Cayusa;
                this.talkToActor(actor);
                break;
            case "H":
                actor = Actor.Guide;
                this.talkToActor(actor);
                break;
            case "V":
                this.displayActor();
                break;
            case "P":
                ActorView actorView = new ActorView();
                actorView.display();
                break;
            default:
                ErrorView.display(this.getClass().getName(), "Error reading input: Invalid entry. Try again.");
                this.display();
            
            //case "I":
                //actor = Actor.Wife;
            //case "J":
                //actor = Actor.Son;
            //case "K":
                //actor = Actor.Daughter;
                
        }
        return false;
    
    
    }

    private void displayActor() {
            this.console.println("\n\n         ACTOR LIST          \n");
            this.console.printf("%n%-20s%20s", "Name", "Description");
            this.console.printf("%n%-20s%20s", "------", "-----------");
          
        Actor[] actor = Actor.values();    
        for (Actor stop : actor) {
            this.console.printf("%n%-20s%20s", stop.getName(), stop.getDescription());
            }
        this.display();
           }

    private void talkToActor(Actor actor) {
        // get dialog.
        CharacterDialog[] characterDialog = CharacterDialog.values();
        int index = GameControl.getRandom(18);
        String dialog = characterDialog[index].getDialog();
        this.console.println("\n==========================================="
                           +"\nHello, my name is " + actor.getName() + "."
                           +"\n" + actor.getExplanation()
                           +"\n" + dialog
                           +"\n===========================================");
        this.display();
    }
    
}
