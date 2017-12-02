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

        }
        return false;

    }

    private void printReport() {
        
        String filePath = this.getFilePath();
        String list = "\n"
               +"\n----------------------------------------------------"              
                +"\n List of map loctations                            |"
                +"\n----------------------------------------------------"
                +"\n   Name, Scene Type, Miles from Start"
                +"\n";
        Location[][] locations = OregonTrail.getCurrentGame().getMap().getLocations();
        for (Location[] location : locations) {
            for (Location stop : location) {
                list += "\n" + stop.getSymbol() + ", " + stop.getPlace().getDescription() 
                        + ", " + stop.getPlace().getMilesFromStart();
                
            }
        }
        this.console.print(list);
        try {
            // save report to specified file.
        printMapReport(list, filePath);
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
        } catch (IOException ex) {
            ErrorView.display(this.getClass().getName(), "Error reading input: " + ex.getMessage());
            
        }
        return value;
}

    private void printMapReport(String list, String filePath) {
        
        // try with resources will auto close at end
        try (PrintWriter out = new PrintWriter(filePath)) {
            // print title and column headings
            out.println("\n\n       LIST OF MAP LOCATIONS       \n");
            out.printf("%n%-10s%20s%10s", "Symbol", "Description", "Scene Type");
            out.printf("%n%-10s%20s%10s", "------", "-----------", "----------");
            
            // print symbol, description, and scenetype of each item
        for (Location[] location : OregonTrail.getCurrentGame().getMap().getLocations()) {
            for (Location stop : location) {
                out.printf("%n%-10s%20s%10s", stop.getSymbol(), stop.getPlace().getDescription(),stop.getScene().getDescription());
                
            }
        }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MapView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
