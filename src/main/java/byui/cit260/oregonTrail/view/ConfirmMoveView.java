/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;

import byui.cit260.oregonTrail.control.GameControl;
import byui.cit260.oregonTrail.control.MapControl;
import byui.cit260.oregonTrail.exceptions.GameControlException;
import byui.cit260.oregonTrail.exceptions.InventoryControlException;
import byui.cit260.oregonTrail.exceptions.MapControlException;
import byui.cit260.oregonTrail.model.Game;
import byui.cit260.oregonTrail.model.Location;
import byui.cit260.oregonTrail.model.Scene;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;
import oregonTrail.OregonTrail;

/**
 *
 * @author Dresen_HP
 */
class ConfirmMoveView extends View{
    private Point newCoordinates;
    private int daysToNext;
    private double foodRequired;

    
    public ConfirmMoveView(double food, int days, Point coordinates) {
        super("\nTraveling to next location will take " + food + " food and " + days + " days."
                    + "\nConfirm? Y/N");
        
        newCoordinates = coordinates;
        daysToNext = days;
        foodRequired = food;
    }
    
    @Override
    public boolean doAction(String value) {
        value = value.toUpperCase();
        
        switch (value) {
            case "Y":
            // move to new location.
                // find location, days to next.
            try {
                MapControl.moveLocation(newCoordinates, foodRequired, daysToNext);
                
                
                
            } catch (MapControlException ex) {
                ErrorView.display(this.getClass().getName(), ex.getMessage());
                return false;
            } catch (InventoryControlException ex) {
                ErrorView.display(this.getClass().getName(), ex.getMessage());
                return false;
            }
            // get scene
            Location newLocation = null;
        {
            try {
                newLocation = MapControl.getCurrentLocation();
            } catch (MapControlException ex) {
                ErrorView.display(this.getClass().getName(), ex.getMessage());
                return false;
            }
        }
            String scene = newLocation.getScene().getName();
            welcomeBanner();
            deliverNextView();
                // deliver correct view.
            break;
        case "N":
            this.console.println("\nMove cancelled.");
            GameMenuView gameMenuView = new GameMenuView();
            gameMenuView.displayMap();
            break;
        default:
            ErrorView.display(this.getClass().getName(), "\nError reading input: Invalid Entry. Please enter Y or N.");
            display();
            break;
        } return false;
    }

    public void welcomeBanner() {
        Location location = null;
        try {
            location = MapControl.getCurrentLocation();
        } catch (MapControlException ex) {
            ErrorView.display(this.getClass().getName(), "\nError reading input: Invalid Entry. Please enter Y or N.");
            display();
        }
        String scene = location.getScene().getName();
        String date = "";
        try {
            date = GameControl.thisDay();
        } catch (GameControlException ex) {
            ErrorView.display(this.getClass().getName(), ex.getMessage());        
        }
        Game game = OregonTrail.getCurrentGame();
        String description = location.getScene().getDescription();

        
        this.console.println("\n****************************************************"
                              + "\n Welcome to " + location.getPlace().getDescription() 
                              +"\n****************************************************"
                              +"\n----------------------------------------------------"
                              +"\nDate: " +   date   
                              +"\nMiles Traveled: " +   game.getMilesTraveled()
                              +"\nPercent Complete: " +  (int) Math.floor(game.getPercentComplete() * 100) + "%"
                              +"\n----------------------------------------------------"
                              +"\nThis is a " + scene + " location." 
                              +"\n" + description
                              +"\n----------------------------------------------------");
                              
        
        /* create new sceneView that displayes welcome banner with location name 
        * scene description, date, miles traveled.It will also have a dynamic menu 
        *based on which type of scene it is. Menu options can be located in SceneType 
        enum list and can be numbered 1-4. Then choice is collected and nested switch
        * statement sends person to correct scene view. Final game menu will not have
        all options. Option to purchase goods will only be available at fort scene.
        River ford will only be available at river scene. Barter, pace, hunt, inventory
        map will be available everywhere.
        */
        
    }

    public void deliverNextView() {
        
        SceneView sceneView = null;
        try {
            sceneView = new SceneView();
        } catch (MapControlException ex) {
            ErrorView.display(this.getClass().getName(), ex.getMessage());
            display();
        }
        sceneView.display();    
    }
    
}


