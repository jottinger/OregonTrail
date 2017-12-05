/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;

import byui.cit260.oregonTrail.control.GameControl;
import byui.cit260.oregonTrail.control.MapControl;
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
                MoveLocationView moveLocationView = new MoveLocationView();
                moveLocationView.display();
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
