/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package byui.cit260.oregonTrail.view;

import byui.cit260.oregonTrail.control.InventoryControl;
import byui.cit260.oregonTrail.exceptions.InventoryControlException;
import byui.cit260.oregonTrail.model.Game;
import byui.cit260.oregonTrail.model.InventoryItem;
import byui.cit260.oregonTrail.model.Location;
import java.util.logging.Level;
import java.util.logging.Logger;
import oregonTrail.OregonTrail;

/**
 *
 * @author Dresen_HP
 */
public class GameMenuView extends View{
    
    // constructor function
    public GameMenuView() {
        super("\n"
                +"\n----------------------------------------------------"
                    +"\n| Game Menu                                        |"
                    +"\n----------------------------------------------------"
                    +"\nB - Barter"
                    +"\nC - Change Pace"
                    +"\nF - Ford River"
                    +"\nG - Hire Guide"
                    +"\nV - View Inventory"
                    +"\nH - Hunt"
                    +"\nP - Purchase Goods"
                    +"\nM - Travel to Next Location"
                    +"\nT - Talk to Locals"
                    +"\nQ - Quit"
                    +"\n----------------------------------------------------"
                    +"\n"
                    +"\n Please enter your choice:");
   
    }
    @Override
    public void display() {  //called from main() in OregonTrail.java
            boolean done = false; // set flag to not done
            MainMenuView mainMenuView = new MainMenuView();
            do {
                //prompt for and get player's name
                String value = this.getInput(); // calls getPlayersName() from this class, stores in string playersName
                if (value.toUpperCase().equals("Q")) { // user wants to quit 
                    done = true;//exit the game
                } else {
                    done = this.doAction(value);// Calls doAction()in this class and passes in name. Return value changes boolean to true to exit do while loop.
  
                }
                //do the requested action and display the next view
            } while (!done);
    }
    
   @Override
   public boolean doAction(String value) {
       value = value.toUpperCase();
       
       switch (value) {
           case "B":
               BarterMenuView barterView = new BarterMenuView();
               barterView.display();
               break;
           case "C":
               ChangePaceView changePaceView = new ChangePaceView();
               changePaceView.display();
               break;
           case "F":
               RiverMenuView riverMenuView = new RiverMenuView();
               riverMenuView.display();
               break;
           case "G":
               GuideMenuView guideMenuView = new GuideMenuView();
               guideMenuView.display();
               break;
           case "V":
               InventoryView inventoryView = new InventoryView();
               inventoryView.display();
               break;
           case "H":
               HuntView huntView = new HuntView();
               huntView.display();
               break;
           case "P":
               String inventory = "";

                {
                   try {
                       inventory = InventoryControl.displayInventoryQuantityPrice();

                       /*       if (inventory == null) 
            throw new InventoryControlException();*/
                   } catch (InventoryControlException ex) {
                       ErrorView.display(this.getClass().getName(), "Error reading data: Can not display inventory because player inventory is null. "
                               + "Please start new game to fix problem.");
                       MainMenuView mainMenuView = new MainMenuView();
                       mainMenuView.display();
                   }
               }

               PurchaseGoodsView purchaseGoodsView;
               try {
                   purchaseGoodsView = new PurchaseGoodsView();
                   purchaseGoodsView.display();
               } catch (InventoryControlException ex) {
                   ErrorView.display(this.getClass().getName(),
                           "Error reading input: " + ex.getMessage());
                   MainMenuView mainMenuView = new MainMenuView();
                   mainMenuView.display();
               }
               break;
           case "M":
               this.displayMap();
               break;
           case "T":
               TalkToLocalsView talktoLocalsView = new TalkToLocalsView();
               talktoLocalsView.display();
               break;
           default:
               ErrorView.display(this.getClass().getName(), "*** Error: invalid choice entered. Try again. ***");
               
               
           
       } return false;
   }

    public void displayMap() {
        //game = get the currentGame from the main class
        Game game = OregonTrail.getCurrentGame();
        //locations = get the 2-D locations array from the map
        Location[][] locations = game.getMap().getLocations();
        //Print the title
        int i = 1;
        this.console.println(  "\n*******************************************"
                            +"\n*            The Oregon Trail             *"
                            +"\n*-----------------------------------------*"
                            +"\n      1       2       3       4       5       "
                + "    ");
        for (Location[] row : locations) {
            this.console.print("\n*-----------------------------------------*"
                              + "\n" + i + " " );
            i++;
        
            for (Location location : row){
                this.console.print("|   " + location.getSymbol() + "   ");
                
            }
        }       
        this.console.println("\n*******************************************");
        MapView mapview = new MapView();
        mapview.display();
        

    }
}

