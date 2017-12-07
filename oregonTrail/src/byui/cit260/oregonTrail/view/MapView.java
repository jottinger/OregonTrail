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
                + "\nP - Print list of map locations"
                + "\nQ - Quit to previous menu"
                + "\n----------------------------------------------------"
                + "\n"
                + "\n Please enter your choice:");
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
            default:
                ErrorView.display(this.getClass().getName(), "*** Error: invalid choice entered. Try again. ***");
                GameMenuView gameMenuView = new GameMenuView();
                gameMenuView.displayMap();
        }
        return false;

    }

    
}
