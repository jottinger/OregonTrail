/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;
import byui.cit260.oregonTrail.model.Actor;
import java.io.FileNotFoundException;
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
public class PrintActorView extends View {
    public PrintActorView() {
        super("\nEnter the file path where you want the report to be saved.");
    }
    
    @Override
    public boolean doAction(String filePath) {
        boolean done = false;
        

        try (PrintWriter out = new PrintWriter(filePath)) {
            out.println("\n\n         ACTOR LIST          \n");
            out.printf("%n%-20s%20s", "Name", "Description");
            out.printf("%n%-20s%20s", "------", "-----------");
            
            Actor[] actor = Actor.values();
            for (Actor stop: actor) {
                String name = stop.name();
                String description = stop.getDescription();
                out.printf("%n%-20s%-30s", name,  description);
            }
        
        this.console.println("\n File printed successfully to " + filePath);

        
    } 
        catch (IOException ex) {
            ErrorView.display(this.getClass().getName(), "Error saving Locations to file");
    
    }
        return true;
    }
 
}
