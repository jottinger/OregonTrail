/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;

import byui.cit260.oregonTrail.control.GameControl;
import byui.cit260.oregonTrail.control.MapControl;
import byui.cit260.oregonTrail.exceptions.MapControlException;
import byui.cit260.oregonTrail.model.Location;
import byui.cit260.oregonTrail.model.Places;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import oregonTrail.OregonTrail;

/**
 *
 * @author Dresen_HP
 */
public class MapView extends View {
    // class instance variables

    // constructor method
    public MapView() {

        super("\n"
                + "\nM - Move to new location"
                + "\nV - View list of map locations"
                + "\nP - Print list of map locations"
                + "\nQ - Quit to previous menu"
                + "\n----------------------------------------------------"
                + "\n"
                + "\n Please enter your choice:");
    }

    @Override
    public void display() {  //called from main() in OregonTrail.java
        boolean done = false; // set flag to not done
        do {
            //prompt for and get player's name
            String value = this.getInput(); // calls getPlayersName() from this class, stores in string playersName

            if (value.toUpperCase().equals("Q")) // user wants to quit
            //return; //exit the game
            {
                try {
                    SceneView sceneView = new SceneView();
                    sceneView.display();
                } catch (MapControlException ex) {
                    ErrorView.display(this.getClass().getName(), ex.getMessage());
                    return;
                }
            }

            //do the requested action and display the next view
            done = this.doAction(value);// Calls doAction()in this class and passes in name. Return value changes boolean to true to exit do while loop.
        } while (!done);
    }

    @Override
    public boolean doAction(String value) {
        value = value.toUpperCase();

        switch (value) {
            case "M":
                Location location = null;
                 {
                    try {
                        location = MapControl.getCurrentLocation();
                    } catch (MapControlException ex) {
                        ErrorView.display(this.getClass().getName(), ex.getMessage());
                        return false;
                    }
                }
                boolean activity = location.getScene().isActivityDone();
                if (activity) {
                    MoveLocationView moveLocationView = new MoveLocationView();
                    moveLocationView.display();
                } else {
                    ErrorView.display(this.getClass().getName(), "\nError moving Location: "
                            + "\nYou must complete a task at this location before moving on."
                            + "\nSelect option 1 to complete the task");

                    try {
                        SceneView sceneView = new SceneView();
                        sceneView.display();
                    } catch (MapControlException ex) {
                        ErrorView.display(this.getClass().getName(), ex.getMessage());
                        return false;
                    }

                }

                break;
            case "P":
                PrintMapView printMapView = new PrintMapView();
                printMapView.display();
                break;
            case "V":
                this.console.println("\n------------------------------------------------------------");
                this.console.println("\n               LIST OF MAP LOCATIONS       ");
                this.console.println("\n------------------------------------------------------------");
                this.console.printf("%n%-8s%-30s%-15s%-6s", "Symbol", "Description", "Scene Type", "miles");
                this.console.printf("%n%-8s%-30s%-15s%-6s", "------", "-----------", "----------", "-----");

                // print symbol, description, and scenetype of each item
                Location[][] locations = OregonTrail.getCurrentGame().getMap().getLocations();
                for (Location[] place : locations) {
                    for (Location stop : place) {
                        String name = stop.getPlace().name();
                        String description = stop.getPlace().getDescription();
                        String scene = stop.getScene().getName();
                        String miles = Integer.toString(stop.getPlace().getMilesFromStart());
                this.console.printf("%n%-8s%-30s%-15s%-6s", name,  description, scene, miles);
                    }
                }
                this.console.println("\n------------------------------------------------------------\n\n");
                try {
                        SceneView sceneView = new SceneView();
                        sceneView.displayMap();
                    } catch (MapControlException ex) {
                        ErrorView.display(this.getClass().getName(), ex.getMessage());
                        return false;
                    }
                break;

                    
                    default:
                ErrorView.display(this.getClass().getName(), "*** Error: invalid choice entered. Try again. ***");
                GameMenuView gameMenuView = new GameMenuView();
                gameMenuView.displayMap();
        }
        return false;

                }

        }
