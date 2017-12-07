/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;

import byui.cit260.oregonTrail.model.Location;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import oregonTrail.OregonTrail;

/**
 *
 * @author Dresen_HP
 */
public class PrintMapView extends View{
    
    public PrintMapView() {
        super("\nEnter the file path where you want the report to be saved.");
    }

    

    @Override
    public boolean doAction(String filePath) {
        boolean done = false;
     // try with resources will auto close at end
        try (PrintWriter out = new PrintWriter(filePath)) {
            // print title and column headings
            out.println("\n\n               LIST OF MAP LOCATIONS       \n");
            out.printf("%n%-8s%-30s%-15s%-6s", "Symbol", "Description", "Scene Type", "Miles");
            out.printf("%n%-8s%-30s%-15s%-6s", "------", "-----------", "----------", "_____");
            
            // print symbol, description, and scenetype of each item
            Location[][] locations = OregonTrail.getCurrentGame().getMap().getLocations();
        for (Location[] location : locations) {
            for (Location stop : location) {
                String name = stop.getPlace().name();
                String description = stop.getPlace().getDescription();
                String scene = stop.getScene().getName();
                String miles = Integer.toString(stop.getPlace().getMilesFromStart());
                out.printf("%n%-8s%-30s%-15s%-6s", name,  description, scene, miles);
                
            }
        }
        this.console.println("\n File printed successfully to " + filePath);
        
        } catch (IOException ex) {
            ErrorView.display(this.getClass().getName(), "Error saving Locations to file");
            
        }
        GameMenuView gameMenuView = new GameMenuView();
        gameMenuView.displayMap();
        return true;
    }
    
}



